package com.example.ridebookingservice.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.ridebookingservice.entity.Ride;

@Service
public class RideProducer {

    private static final String TOPIC = "ride-requested";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendRideEvent(Ride ride) {
        kafkaTemplate.send("ride-requested", ride);
        System.out.println("Ride event sent to Kafka: " + ride);
    }
}
