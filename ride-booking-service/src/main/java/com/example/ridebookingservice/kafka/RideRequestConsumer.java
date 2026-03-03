package com.example.ridebookingservice.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.example.ridebookingservice.repository.*;
import com.example.ridebookingservice.dto.RideRequest;
//import com.example.ridebookingservice.model.RideRequest;
import com.example.ridebookingservice.entity.Ride;

@Service
public class RideRequestConsumer {

    private final RideRepository rideRepository;
    
    public RideRequestConsumer(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
    }

    @KafkaListener(topics = "ride_bookings", groupId = "ride-booking-group")
    public void consumeRideRequest(Object message) {
        System.out.println("✅ Ride request received: " + message);
        
//        Ride ride = new Ride();
//        
//        ride.setUser(rideRequest.getCustomerName());
//        ride.setPickupLocation(rideRequest.getPickupLocation());
//        ride.setDropLocation(rideRequest.getDropLocation());
//        ride.setStatus("REQUESTED");
//        
//     // SAVE TO DB
//        
//        Ride savedRide = rideRepository.save(ride);
//        
//        System.out.println("Ride saved to DB with ID : " + savedRide.getId());
//        System.out.println("Key :" + record.key());
//        System.out.println("Value :" + record.value());
//        System.out.println("Partition :" + record.partition());
//        System.out.println("Offset :" + record.offset());
//        
        
        // Here we can later add logic to process the ride request
        // like assigning driver, checking availability, etc.
    }
}
