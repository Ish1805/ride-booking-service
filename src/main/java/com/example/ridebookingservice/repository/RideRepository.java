package com.example.ridebookingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.ridebookingservice.entity.Ride;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {
}
