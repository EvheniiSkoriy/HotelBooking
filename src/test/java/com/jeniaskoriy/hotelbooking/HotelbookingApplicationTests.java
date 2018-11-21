package com.jeniaskoriy.hotelbooking;

import com.jeniaskoriy.hotelbooking.exception.UserNotFoundException;
import com.jeniaskoriy.hotelbooking.model.Apartment;
import com.jeniaskoriy.hotelbooking.model.BookedDate;
import com.jeniaskoriy.hotelbooking.model.Category;
import com.jeniaskoriy.hotelbooking.model.User;
import com.jeniaskoriy.hotelbooking.repository.HotelRepository;
import com.jeniaskoriy.hotelbooking.service.HotelService;
import com.jeniaskoriy.hotelbooking.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelbookingApplicationTests {

    @Autowired
    UserService userService;

    @Autowired
    HotelService hotelService;

    @Autowired
    HotelRepository hotelRepository;

    @Test
    public void testFindByName() {
        userService.save(new User(1, "john"));
        userService.save(new User(2, "mary"));
        User user = new User(3, "john");
        User foundUser = userService.findByName("john");
        assertEquals(user.getName(), foundUser.getName());
    }

    @Test
    public void testFindByNameThatNoContain() {
        userService.save(new User(1, "john"));
        userService.save(new User(2, "mary"));
        User foundUser = userService.findByName("scot");
        assertNull(foundUser);
    }

}
