package com.example.ridebookingservice.kafka;

import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

import com.example.ridebookingservice.entity.Ride;
import com.example.ridebookingservice.entity.RideStatus;
import com.example.ridebookingservice.repository.RideRepository;

@Component
public class RideConsumer {

    private final RideRepository rideRepository;

    public RideConsumer(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
    }

    @RetryableTopic(
            attempts = "3",
            backoff = @Backoff(delay = 2000),
            dltTopicSuffix = "-dlt"
    )
    @KafkaListener(topics = "ride-requested", groupId = "ride-group")
    public void consume(Ride ride) {

        System.out.println("Processing ride: " + ride);

        // Simulate failure
        if ("FAIL".equalsIgnoreCase(ride.getPickupLocation())) {
            throw new IllegalArgumentException("Simulated failure");
        }

        // Fetch latest version from DB
        Ride existingRide = rideRepository.findById(ride.getId()).orElseThrow(() ->new IllegalArgumentException("Ride not found"));
        
        // Only assign driver if status is REQUESTED
        if(existingRide.getStatus() == RideStatus.REQUESTED) {
            existingRide.setStatus(RideStatus.DRIVER_ASSIGNED);
            rideRepository.save(existingRide);
            System.out.println("Driver assigned successfully");
        }
        else {
        	System.out.println("No status change needed. Current Status : " + existingRide.getStatus());
        }
    }

    @DltHandler
    public void handleDLT(Ride ride) {
        System.out.println("Moved to DLQ: " + ride);
    }
}
