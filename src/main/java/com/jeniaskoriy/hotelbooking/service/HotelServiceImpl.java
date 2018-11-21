package com.jeniaskoriy.hotelbooking.service;

import com.jeniaskoriy.hotelbooking.model.Apartment;
import com.jeniaskoriy.hotelbooking.model.BookedDate;
import com.jeniaskoriy.hotelbooking.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Override
    public List<Apartment> getFreeApartments(LocalDate date) {

        return hotelRepository.findAll().stream()
                .filter(bookDates ->
                    bookDates.getBookedDates().stream()
                            .noneMatch(d -> d.getBookedDate().equals(date))
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<Apartment> getBookedApartments() {
        List<Apartment> allApartments = hotelRepository.findAll();
        List<Apartment> bookedApartments = new ArrayList<>();
        for (int i = 0; i < allApartments.size(); i++) {
            if (!allApartments.get(i).getBookedDates().isEmpty()) {
                bookedApartments.add(allApartments.get(i));
            }
        }
        return hotelRepository.findAll().stream()
                .filter(bookDates -> !bookDates.getBookedDates().isEmpty())
                .collect(Collectors.toList());
    }

    @Override
    public Apartment findById(Integer id) {
        return hotelRepository.findApartmentById(id);
    }

}
