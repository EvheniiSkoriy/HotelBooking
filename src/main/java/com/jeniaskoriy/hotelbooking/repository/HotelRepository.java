package com.jeniaskoriy.hotelbooking.repository;


import com.jeniaskoriy.hotelbooking.model.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Apartment, Integer> {

    Apartment findApartmentById(Integer id);
}
