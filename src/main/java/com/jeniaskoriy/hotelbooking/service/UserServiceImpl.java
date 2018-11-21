package com.jeniaskoriy.hotelbooking.service;


import com.jeniaskoriy.hotelbooking.model.Apartment;
import com.jeniaskoriy.hotelbooking.model.User;
import com.jeniaskoriy.hotelbooking.repository.HotelRepository;
import com.jeniaskoriy.hotelbooking.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final HotelRepository hotelRepository;


    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public List<Apartment> getBookedApartments(String name) {
        return userRepository.getBookedApartments(name);
    }

    @Override
    public void bookApartment(User user, Apartment apartment, LocalDate date, Integer duration) {
        boolean apartmentPresent = false;
        apartment.addBookedDates(date, duration, user.getName());

        for (Apartment apt : user.getBookedApartments()) {
            if (apt.getId().equals(apartment.getId())) {
                apartmentPresent = true;
            }
        }
        if (!apartmentPresent) {
            user.addApartment(apartment);
        }
        userRepository.saveAndFlush(user);
        hotelRepository.save(apartment);
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }


}
