package com.example.ridebookingservice.dto;

public class PaymentResponse {
    private Long paymentId;
    private Long rideId;
    private String status;
    private String transactionId;

    public PaymentResponse(){}

    public PaymentResponse(Long paymentId, Long rideId, String status, String transactionId){
        this.paymentId = paymentId;
        this.rideId = rideId;
        this.status = status;
        this.transactionId = transactionId;
    }

    public Long getPaymentId() { return paymentId; }
    public Long getRideId() { return rideId; }
    public String getStatus() { return status; }
    public String getTransactionId() { return transactionId; }


}