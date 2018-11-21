
package com.jeniaskoriy.hotelbooking.service;


import com.jeniaskoriy.hotelbooking.model.Apartment;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public interface HotelService {

    List<Apartment> getFreeApartments(LocalDate date);

    List<Apartment> getBookedApartments();

    Apartment findById(Integer id);
}
