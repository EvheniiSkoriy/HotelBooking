package com.jeniaskoriy.hotelbooking.controller;


import com.jeniaskoriy.hotelbooking.dto.ApartmentDTOOut;
import com.jeniaskoriy.hotelbooking.model.Apartment;
import com.jeniaskoriy.hotelbooking.model.BookedDate;
import com.jeniaskoriy.hotelbooking.service.HotelService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @GetMapping("/hotel/booked")
    public List<ApartmentDTOOut> getAllBookedApartments() {
        List<Apartment> bookedApartments = hotelService.getBookedApartments();
        List<ApartmentDTOOut> bookedApartmentsDto = new ArrayList<>();
        for (Apartment apt : bookedApartments) {
            for (BookedDate bd : apt.getBookedDates())
                bookedApartmentsDto
                        .add(new ApartmentDTOOut(apt.getId(),
                                apt.getNumber(),
                                apt.getCategory(),
                                apt.getPrice(),
                                bd.getBookedDate()));
        }
        return bookedApartmentsDto;
    }

    @GetMapping("/{date}")
    public List<ApartmentDTOOut> getAllFreeApartments(
            @PathVariable
            @NotNull
            @DateTimeFormat(pattern = "yyyy-MM-dd")
                    LocalDate date,
            @RequestParam(defaultValue = "number")
                    String sort
    ) {

        List<Apartment> freeApartments = hotelService.getFreeApartments(date);
        if (sort.equals("category")) {
            Collections.sort(freeApartments);
        }

        return freeApartments.stream()
                .map(apartment -> new ApartmentDTOOut(
                        apartment.getId(),
                        apartment.getNumber(),
                        apartment.getCategory(),
                        apartment.getPrice()))
                .collect(Collectors.toList());
    }

}
