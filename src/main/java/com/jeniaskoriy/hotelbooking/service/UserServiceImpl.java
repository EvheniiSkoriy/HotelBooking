package com.jeniaskoriy.hotelbooking.service;


import com.jeniaskoriy.hotelbooking.model.Apartment;
import com.jeniaskoriy.hotelbooking.model.User;
import com.jeniaskoriy.hotelbooking.repository.HotelRepository;
import com.jeniaskoriy.hotelbooking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
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

        apartment.addBookedDates(date,duration,user.getName());
        System.out.println(user.getBookedApartments());
        for(Apartment apt:user.getBookedApartments()){

            System.out.println("USER APARTMENT:"+apt);
            System.out.println("NEW APARTMENT:" + apartment);
            if(apt.getId().equals(apartment.getId())){
                user.addApartment(apartment);
            }
        }
        userRepository.saveAndFlush(user);
        hotelRepository.save(apartment);
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }



}
