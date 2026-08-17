package com.example.ridebookingservice.kafka;

import com.example.ridebookingservice.entity.Ride;
import com.example.ridebookingservice.entity.RideStatus;
import com.example.ridebookingservice.event.RideResponseEvent;
import com.example.ridebookingservice.repository.RideRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

@Component
public class RideConsumer {

    private static final Logger log = LoggerFactory.getLogger(RideConsumer.class);

    private final RideRepository rideRepository;

    public RideConsumer(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
    }

    @RetryableTopic(attempts = "4", backoff = @Backoff(delay = 1000, multiplier = 2.0, maxDelay = 10000), dltTopicSuffix = "-dlt")
    @KafkaListener(topics = "ride-response", groupId = "ride-group")
    public void handleRideResponse(RideResponseEvent event) {
        log.info("Received ride response: {}", event);

        Ride ride = rideRepository.findById(event.getRideId())
                .orElseThrow(() -> new IllegalArgumentException("Ride not found: " + event.getRideId()));

        if (event.getStatus() == RideResponseEvent.Status.ACCEPTED) {
            if (ride.getStatus() == RideStatus.REQUESTED) {
                ride.setStatus(RideStatus.DRIVER_ASSIGNED);
                ride.setDriverId(event.getDriverId());
                rideRepository.save(ride);
                log.info("Ride {} accepted by driver {}", ride.getId(), event.getDriverId());
            } else {
                log.warn("Ride {} not in REQUESTED state (current: {}), ignoring acceptance", ride.getId(), ride.getStatus());
            }
        } else {
            // REJECTED — either no drivers left, or Driver Service is still retrying with another driver.
            // We only need to act if there are truly no drivers left (reason will say so).
            if ("No drivers available".equals(event.getReason())) {
                ride.setStatus(RideStatus.CANCELLED);
                rideRepository.save(ride);
                log.warn("Ride {} cancelled - no drivers available", ride.getId());
            } else {
                log.info("Ride {} rejected by a driver, Driver Service retrying with another", ride.getId());
            }
        }
    }

    @DltHandler
    public void handleDLT(RideResponseEvent event) {
        log.warn("Moved to DLQ: {}", event);
    }
}