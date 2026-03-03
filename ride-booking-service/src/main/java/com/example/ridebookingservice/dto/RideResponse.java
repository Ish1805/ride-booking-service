package com.example.ridebookingservice.dto;
import com.example.ridebookingservice.entity.RideStatus;

public class RideResponse {

	private Long id;
    private String pickupLocation;;
    private String dropLocation;
    private String userName;
    private RideStatus status;
    
    public RideResponse(Long id, String pickupLocation, String dropLocation, String userName, RideStatus status) {
    	this.id = id;
    	this.pickupLocation = pickupLocation;
    	this.dropLocation = dropLocation;
    	this.userName = userName;
    	this.status = status;
    }
      
    public Long getId() {
    	return id;
    }
    
    public String getPickup() {
    	return pickupLocation;
    }
    
    public String getDrop() {
    	return dropLocation;
    }
    
    public String getUserName() {
    	return userName;
    }
    
    public RideStatus getStatus() {
    	return status;
    }
    
}
