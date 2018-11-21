
package com.jeniaskoriy.hotelbooking.service;


import com.jeniaskoriy.hotelbooking.model.Apartment;
import com.jeniaskoriy.hotelbooking.model.User;


import java.time.LocalDate;
import java.util.List;


public interface UserService {

    void save(User user);
    List<Apartment> getBookedApartments(String name);
    void bookApartment(User user,Apartment apartment, LocalDate data,Integer duration);
    User findByName(String name);
}
