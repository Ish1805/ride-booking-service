package com.example.ridebookingservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ridebookingservice.dto.ApiResponse;
import com.example.ridebookingservice.dto.RideRequest;
import com.example.ridebookingservice.dto.RideResponse;
import com.example.ridebookingservice.kafka.RideProducer;
import com.example.ridebookingservice.entity.CancellationType;
import com.example.ridebookingservice.entity.Ride;
import com.example.ridebookingservice.entity.RideStatus;
import com.example.ridebookingservice.kafka.RideEventProducer;
//import com.example.ridebookingservice.producer.RideRequestProducer;
import com.example.ridebookingservice.repository.RideRepository;
import com.example.ridebookingservice.service.RideService;

import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/rides")
@Tag(name = "Ride APIs", description = "Operation related to ride booking")
public class RideController {
	
	private final RideService rideService;
	
	public RideController(RideService rideService) {
		this.rideService = rideService;
	}
	
//	private final RideRequestProducer producer;
//	
//	public RideController(RideRequestProducer producer) {
//		this.producer = producer;
//	}
//	
//	@PostMapping("/requests")
//	public String requestRide(@RequestBody RideRequest request) {
//		producer.sendRideRequest(request);
//		
//		return "Ride Request sent to Kafka!";
//	}
	
	
	@Autowired
	private RideEventProducer rideEventProducer;
	
//	@PostMapping("/requests")
//	public String requestRide(@RequestBody RideRequest rideRequest) {
//		rideEventProducer.sendRideRequest(rideRequest);
//		return "Ride Request placed successfully";
//	}

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private RideProducer rideProducer;

    //POST API LOGIC
     
@Operation(summary = "Book a ride")
@io.swagger.v3.oas.annotations.responses.ApiResponse(
		responseCode = "200", description = "Ride booked succesfully"
	)
@io.swagger.v3.oas.annotations.responses.ApiResponse(
		responseCode = "400", description = "invalid Request"
	)

    @PostMapping("/book")
    public ApiResponse<RideResponse> bookRide(@Valid @RequestBody RideRequest request) {
        return new ApiResponse<>(true, rideService.bookRide(request));
    }
    
    //GET API LOGIC

    @GetMapping("/{id}")
    public Ride getRide(@PathVariable Long id) {
        return rideRepository.findById(id).orElse(null);
    }
    
    //PUT API LOGIC 
    
    @PutMapping("/{id}/status")
    public ApiResponse<RideResponse> updateRideStatus(@PathVariable Long id, @RequestParam RideStatus status, @RequestParam(required = false) CancellationType cancellationType) {
    	return new ApiResponse<>(true, rideService.updateRideStatus(id, status, cancellationType));
    }

}
