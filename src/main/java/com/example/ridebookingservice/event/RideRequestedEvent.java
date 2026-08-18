package com.example.ridebookingservice.event;

public class RideRequestedEvent {

    private Long rideId;
    private String pickupLocation;
    private String dropLocation;
    private String userName;

    public RideRequestedEvent() {}

    public RideRequestedEvent(Long rideId, String pickupLocation, String dropLocation, String userName) {
        this.rideId = rideId;
        this.pickupLocation = pickupLocation;
        this.dropLocation = dropLocation;
        this.userName = userName;
    }

    public Long getRideId() { return rideId; }
    public void setRideId(Long rideId) { this.rideId = rideId; }

    public String getPickupLocation() { return pickupLocation; }
    public void setPickupLocation(String pickupLocation) { this.pickupLocation = pickupLocation; }

    public String getDropLocation() { return dropLocation; }
    public void setDropLocation(String dropLocation) { this.dropLocation = dropLocation; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    @Override
    public String toString() {
        return "RideRequestedEvent{rideId=" + rideId + ", pickupLocation='" + pickupLocation + "', dropLocation='" + dropLocation + "', userName='" + userName + "'}";
    }
}