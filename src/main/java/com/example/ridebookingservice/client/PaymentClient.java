package com.example.ridebookingservice.client;

import com.example.ridebookingservice.dto.PaymentRequest;
import com.example.ridebookingservice.dto.PaymentResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PaymentClient {

    private static final Logger log = LoggerFactory.getLogger(PaymentClient.class);
    private static final String PAYMENT_SERVICE_URL = "http://localhost:8084/payments";

    private final RestTemplate restTemplate;

    public PaymentClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = "paymentService", fallbackMethod = "paymentFallback")
    @Retry(name = "paymentService")
    public PaymentResponse charge(Long rideId, Long riderId, Double amount, String method) {
        PaymentRequest request = new PaymentRequest(rideId, riderId, amount, method);
        log.info("Calling Payment Service for ride {}", rideId);
        return restTemplate.postForObject(PAYMENT_SERVICE_URL, request, PaymentResponse.class);
    }

    public PaymentResponse paymentFallback(Long rideId, Long riderId, Double amount, String method, Throwable t) {
        log.warn("Payment Service unavailable for ride {}, reason: {}", rideId, t.getMessage());
        return new PaymentResponse(null, rideId, "UNAVAILABLE", null);
    }
}