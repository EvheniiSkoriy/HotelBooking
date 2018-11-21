package com.jeniaskoriy.hotelbooking.controller;


import com.jeniaskoriy.hotelbooking.dto.ApartmentDTOOut;
import com.jeniaskoriy.hotelbooking.dto.BookingList;
import com.jeniaskoriy.hotelbooking.dto.UserDTOIn;
import com.jeniaskoriy.hotelbooking.exception.ApartmentNotFoundException;
import com.jeniaskoriy.hotelbooking.exception.ApartmentWasBookedException;
import com.jeniaskoriy.hotelbooking.exception.UserNotFoundException;
import com.jeniaskoriy.hotelbooking.model.AdditionalOption;
import com.jeniaskoriy.hotelbooking.model.Apartment;
import com.jeniaskoriy.hotelbooking.model.BookedDate;
import com.jeniaskoriy.hotelbooking.model.User;
import com.jeniaskoriy.hotelbooking.service.HotelService;
import com.jeniaskoriy.hotelbooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    private UserService userService;
    private HotelService hotelService;

    @Autowired
    public UserController(UserService userService, HotelService hotelService) {
        this.userService = userService;
        this.hotelService = hotelService;
    }

    @PostMapping("/register")
    public String registerNewUser(@RequestBody UserDTOIn userDTOIn) {
        User user = new User(userDTOIn.getName());

        userService.save(user);
        return user.getName() + " was successful created";
    }

    @PostMapping("/hotel/book")
    public String bookApartment(
            @RequestBody @NotNull @Valid BookingList bookingList) {

        Apartment apartment = hotelService.findById(bookingList.getRoomId());
        User user = userService.findByName(bookingList.getName());
        if(user == null){
            throw new UserNotFoundException(bookingList.getName());
        }else if(apartment == null){
            throw new ApartmentNotFoundException(bookingList.getRoomId());
        }
        for (BookedDate date : apartment.getBookedDates()) {
            if (date.getBookedDate().equals(bookingList.getDate())) {
                throw new ApartmentWasBookedException();
            }
        }
        userService.bookApartment(user, apartment, bookingList.getDate(), bookingList.getDuration());

        return "Successful";
    }

    @GetMapping("/hotel/{username}")
    public List<ApartmentDTOOut> getAllBookedApartments(
            @PathVariable String username
    ) {
        User user = userService.findByName(username);
        List<Apartment> bookedApartments;
        if (user != null) {
            bookedApartments = user.getBookedApartments();
        } else {
            throw new UserNotFoundException(username);
        }
        List<ApartmentDTOOut> bookedApartmentsOut = new ArrayList<>();
        for(Apartment apartment:bookedApartments){
            System.out.println(apartment);
            for(BookedDate bookedDate:apartment.getBookedDates()){
                System.out.println("BOOKED DATE: " + bookedDate);
                if(bookedDate.getUsername().equals(user.getName())){
                    bookedApartmentsOut.add(new ApartmentDTOOut(
                            apartment.getId(),
                            apartment.getNumber(),
                            apartment.getCategory(),
                            apartment.getPrice(),
                            bookedDate.getBookedDate()));
                }
            }
        }
        System.out.println("----------------------");
        return bookedApartmentsOut;
//        return bookedApartments
//                .stream()
//                .map(apartment -> new ApartmentDTOOut(
//                        apartment.getId(),
//                        apartment.getNumber(),
//                        apartment.getCategory(),
//                        apartment.getPrice()))
//                .collect(Collectors.toList());

    }

    @GetMapping("/hotel/{username}/price")
    public double getPriceForAllBookedApartments(
                    @PathVariable
                    String username
    ) {
        User user = userService.findByName(username);
        List<Apartment> bookedApartments;
        if (user != null) {
            bookedApartments = user.getBookedApartments();
        } else {
            throw new UserNotFoundException(username);
        }
        return calculatePrice(bookedApartments);
    }

    private double calculatePrice(List<Apartment> apartments) {
        double sum = 0;
        for (Apartment apt : apartments) {
            sum += apt.getPrice();
            for(AdditionalOption ao:apt.getAdditionalOptions()){
                sum+=ao.getPrice();
            }
        }
        return sum;
    }
}
