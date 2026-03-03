package com.example.ridebookingservice.service;

import com.example.ridebookingservice.dto.*;
import com.example.ridebookingservice.entity.*;
import com.example.ridebookingservice.repository.RideRepository;
import com.example.ridebookingservice.kafka.RideProducer;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class RideService {
	
	private final RideRepository rideRepository;
	private final RideProducer rideProducer;
	
	public RideService(RideRepository rideRepository, RideProducer rideProducer) {
		this.rideRepository = rideRepository;
		this.rideProducer = rideProducer;
	}
	
// -----------------------------------LOGGER---------------------------------------------------------------------------
// -----------------------------------LOGGER---------------------------------------------------------------------------
	
	private static final Logger log = LoggerFactory.getLogger(RideService.class);
	
// -----------------------------------BOOK RIDE------------------------------------------------------------------------
// -----------------------------------BOOK RIDE------------------------------------------------------------------------
	
	
	public RideResponse bookRide(RideRequest request) {
		
		Ride ride = new Ride();
		//log.info("Booking Ride for user : {}", request.getUserName());
    	ride.setPickupLocation(request.getPickupLocation());
    	ride.setDropLocation(request.getDropLocation());
    	ride.setUserName(request.getUserName());
    	ride.setStatus(RideStatus.REQUESTED);
    	
    	Ride saved = rideRepository.save(ride);
    	rideProducer.sendRideEvent(saved);
    	
    	return mapToResponse(saved);
	}

// -----------------------------------UPDATE RIDE------------------------------------------------------------------------
// -----------------------------------UPDATE RIDE------------------------------------------------------------------------

		
	public RideResponse updateRideStatus(Long id, RideStatus status, CancellationType cancellationType) {
		Ride ride = rideRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Ride not found"));
    	RideStatus current = ride.getStatus();
    	
    	//Handle cancellation separately
    	
    	if(status == RideStatus.CANCELLED) {
    		if(cancellationType == null) {
    			throw new IllegalArgumentException("Cancellation type required");
    		}
    		
    		if(cancellationType == CancellationType.USER) {
    			if(current == RideStatus.REQUESTED || current == RideStatus.DRIVER_ASSIGNED) {
    				ride.setStatus(RideStatus.CANCELLED);
    			} else {
    				throw new IllegalArgumentException("User cannot cancel at this stage");
    			}
    		} else if(cancellationType == CancellationType.DRIVER) {
    			if(current == RideStatus.DRIVER_ASSIGNED) {
    				ride.setStatus(RideStatus.REQUESTED);
    				
    				// Auto trigger retry machine
    				
    				rideProducer.sendRideEvent(ride);
    			} else {
    				throw new IllegalArgumentException("Driver cannot cancel at this stage");
    			}
    		}
    	} else {
    		if(!isValidTransition(current, status)) {
    			throw new IllegalArgumentException("Invalid status transition");
    		}
    		ride.setStatus(status);
    	}
    	
    	Ride updated = rideRepository.save(ride);
    	
    	return mapToResponse(updated);
    	
	}
	
// -----------------------------------STATE MACHINE------------------------------------------------------------------------
// -----------------------------------STATE MACHINE------------------------------------------------------------------------
	
	
	private boolean isValidTransition(RideStatus current, RideStatus next) {
		switch(current) {
		case REQUESTED:
		     return next == RideStatus.DRIVER_ASSIGNED;
		
		case DRIVER_ASSIGNED:
		     return next == RideStatus.STARTED;
		     
		case STARTED:
		     return next == RideStatus.COMPLETED;
		     
		default:
			 return false;
		}
	}

	
// -----------------------------------GET RIDE------------------------------------------------------------------------
// -----------------------------------GET RIDE------------------------------------------------------------------------
	
	
	public Ride getRide(Long id) {
		return rideRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Ride not found"));
	}
	
// -----------------------------------MAPPER------------------------------------------------------------------------
// -----------------------------------MAPPER------------------------------------------------------------------------

	private RideResponse mapToResponse(Ride ride) {
		return new RideResponse(
				ride.getId(),
				ride.getPickupLocation(),
				ride.getDropLocation(),
				ride.getUserName(),
				ride.getStatus()
			);
	}
}