package com.example.ridebookingservice.event;

public class RideResponseEvent {

    public enum Status { ACCEPTED, REJECTED }

    private Long rideId;
    private Long driverId;
    private Status status;
    private String reason;

    public RideResponseEvent() {}

    public Long getRideId() { return rideId; }
    public void setRideId(Long rideId) { this.rideId = rideId; }
    public Long getDriverId() { return driverId; }
    public void setDriverId(Long driverId) { this.driverId = driverId; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    @Override
    public String toString() {
        return "RideResponseEvent{rideId=" + rideId + ", driverId=" + driverId + ", status=" + status + ", reason='" + reason + "'}";
    }
}