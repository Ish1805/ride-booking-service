package com.example.ridebookingservice.kafka;

import com.example.ridebookingservice.dto.RideRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class RideEventProducer {
	private final KafkaTemplate<String, Object> kafkaTemplate;
	
	//private KafkaTemplate<String, RideRequest> kafkaTemplate;
	
	public RideEventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
	public void sendRideRequest(Object message) {
		kafkaTemplate.send("ride-requests", message);
		System.out.println("Ride Request sent to kafka :" + message);
	}
	
}
