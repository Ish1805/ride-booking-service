package com.example.ridebookingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.Bean;


@EnableKafka
@EnableRetry
@SpringBootApplication(scanBasePackages = "com")
@ComponentScan(basePackages = "com.example.ridebookingservice")
public class RideBookingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RideBookingServiceApplication.class, args);
	}

	@Bean
//	public Server h2Server() throws java.sql.SQLException {
//		return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9093").start();
//	}

	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
}