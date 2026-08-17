package com.example.ridebookingservice.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.ridebookingservice.entity.Ride;
import com.example.ridebookingservice.event.RideRequestedEvent;

@Service
public class RideProducer {

    private static final Logger log = LoggerFactory.getLogger(RideProducer.class);

    private static final String TOPIC = "ride-requested";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendRideEvent(Ride ride) {
        RideRequestedEvent event = new RideRequestedEvent(ride.getId(),
                ride.getPickupLocation(), ride.getDropLocation(), ride.getUserName());
        kafkaTemplate.send(TOPIC, event);
        log.info("Ride event sent to Kafka: {}", event);
    }
}
