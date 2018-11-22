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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


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
    public ResponseEntity<?> registerNewUser(@RequestBody UserDTOIn userDTOIn) {
        User user = new User(userDTOIn.getName());

        try {
            userService.save(user);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/hotel/book")
    public ResponseEntity<?> bookApartment(
            @RequestBody @NotNull @Valid BookingList bookingList) {

        Apartment apartment = hotelService.findById(bookingList.getRoomId());
        User user = userService.findByName(bookingList.getName());

        if (user == null) {
            throw new UserNotFoundException(bookingList.getName());
        } else if (apartment == null) {
            throw new ApartmentNotFoundException(bookingList.getRoomId());
        }

        LocalDate durationDays = bookingList.getDate();
        for (int i = 0; i < bookingList.getDuration(); i++) {
            for (int j = 0; j < apartment.getBookedDates().size(); j++) {
                if (apartment.getBookedDates().get(j).getBookedDate().equals(durationDays)) {
                    throw new ApartmentWasBookedException();
                }
            }
            durationDays = durationDays.plusDays(1);
        }

        userService.bookApartment(user, apartment, bookingList.getDate(), bookingList.getDuration());

        return new ResponseEntity<>(HttpStatus.OK);
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
        for (Apartment apartment : bookedApartments) {
            for (BookedDate bookedDate : apartment.getBookedDates()) {
                if (bookedDate.getUsername().equals(user.getName())) {
                    bookedApartmentsOut.add(new ApartmentDTOOut(
                            apartment.getId(),
                            apartment.getNumber(),
                            apartment.getCategory(),
                            apartment.getPrice(),
                            bookedDate.getBookedDate()));
                }
            }
        }
        return bookedApartmentsOut;

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
        return calculatePrice(user.getName(), bookedApartments);
    }

    private double calculatePrice(String username, List<Apartment> apartments) {
        double sum = 0;
        for (Apartment apt : apartments) {
            for(BookedDate bookedDate: apt.getBookedDates()){
                if(username.equals(bookedDate.getUsername())){
                    sum += apt.getPrice();
                }
            }
            for (AdditionalOption ao : apt.getAdditionalOptions()) {
                sum += ao.getPrice();
            }
        }
        return sum;
    }
}
