# 🚖 Ride Booking Service (Event-Driven Microservice)

A backend microservice for ride booking built using **Spring Boot**, **Apache Kafka**, and **H2 Database**.  
This project demonstrates event-driven architecture, layered design, role-based business logic, and API documentation using OpenAPI (Swagger).

---

## 🛠 Tech Stack

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- Apache Kafka
- H2 Database
- OpenAPI / Swagger
- Maven

---

## 🏗 Architecture Overview

This service follows a layered architecture:

Controller → Service → Repository → Database  
Controller → Kafka Producer → Kafka Topic → Kafka Consumer → Service

### Key Features

- Ride booking API
- Ride status update
- Fetch ride by ID
- Role-based cancellation logic
- Event-driven communication using Kafka
- Centralized exception handling
- DTO-based request/response design
- Manual Kafka acknowledgment
- API documentation via Swagger

---

## 📡 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/rides/book` | Book a new ride |
| GET | `/rides/{id}` | Get ride details |
| PUT | `/rides/{id}/status` | Update ride status |

---

## 📘 Swagger Documentation

After running the application:

http://localhost:8080/swagger-ui/index.html


---

## 📨 Kafka Integration

- Produces ride events
- Consumes ride events
- Manual acknowledgment enabled
- Configurable consumer group
- JSON serialization/deserialization

Kafka runs on: localhost:9092

---

## ▶ How To Run

### 1️⃣ Start Kafka & Zookeeper

Make sure Kafka broker is running on `localhost:9092`.

### 2️⃣ Run Application

From IDE: Run as → Spring Boot App

Or via terminal:
mvn clean install
mvn spring-boot:run


---

## 🚀 Future Enhancements

- JWT Authentication (Spring Security)
- Dockerization
- Actuator Monitoring
- Pagination & Sorting
- CI/CD Pipeline Integration





