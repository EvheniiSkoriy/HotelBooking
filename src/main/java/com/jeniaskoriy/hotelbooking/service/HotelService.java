
package com.jeniaskoriy.hotelbooking.service;


import com.jeniaskoriy.hotelbooking.model.Apartment;

import java.time.LocalDate;
import java.util.List;

public interface HotelService {

    List<Apartment> getFreeApartments(LocalDate date);

    List<Apartment> getBookedApartments();

    Apartment findById(Integer id);
}
