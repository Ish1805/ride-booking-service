package com.example.ridebookingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.retry.annotation.EnableRetry;

@EnableKafka
@EnableRetry
@SpringBootApplication(scanBasePackages = "com")
@ComponentScan(basePackages = "com.example.ridebookingservice")
public class RideBookingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RideBookingServiceApplication.class, args);
	}

}
