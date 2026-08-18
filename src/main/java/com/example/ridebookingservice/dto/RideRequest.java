package com.example.ridebookingservice.dto;
import jakarta.validation.constraints.NotBlank;

//public class RideRequest {
//
//	    private String user;
//	    private String pickupLocation;
//	    private String dropLocation;
//
//}
public class RideRequest {

	@NotBlank(message = "Pickup Location is required")
	private String pickupLocation;
	
	@NotBlank(message = "Drop Location is required")
	private String dropLocation;
	
	@NotBlank(message = "Username is required")
	private String userName;
	
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public void setPickupLoaction(String pickupLoaction) {
		this.pickupLocation = pickupLocation;
	}
	
	public String getPickupLocation() {
		return this.pickupLocation;
	}
	
	public void setDropLocation(String dropLocation) {
		this.dropLocation = dropLocation;
	}
	
	public String getDropLocation() {
		return this.dropLocation;
	}
	
}

