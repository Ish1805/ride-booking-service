//package com.example.ridebookingservice.kafka;
//
//import com.example.ridebookingservice.dto.RideRequest;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//

//public class RideRequestProducer {
//
//	private final KafkaTemplate<String, RideRequest> kafkaTemplate;
//	
//	public RideRequestProducer(KafkaTemplate<String, RideRequest> kafkaTemplate) {
//		this.kafkaTemplate = kafkaTemplate;
//	}
//	
//	public void sendRideRequest(RideRequest request) {
//		kafkaTemplate.send("ride_requests", request);
//		System.out.println("Ride Request sent to kafka :" + request);
//	}
//}
