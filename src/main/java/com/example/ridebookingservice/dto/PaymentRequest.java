package com.example.ridebookingservice.dto;

public class PaymentRequest {
    private Long rideId;
    private Long riderId;
    private Double amount;
    private String method;

    public PaymentRequest() {}

    public PaymentRequest(Long rideId, Long riderId, Double amount, String method) {
        this.rideId = rideId;
        this.riderId = riderId;
        this.amount = amount;
        this.method = method;
    }

    public Long getRideId() { return rideId; }
    public Long getRiderId() { return riderId; }
    public Double getAmount() { return amount; }
    public String getMethod() { return method; }
}