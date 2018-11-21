package com.jeniaskoriy.hotelbooking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ApartmentWasBookedException extends RuntimeException {

    public ApartmentWasBookedException() {
        super("Apartment was booked early");
    }

    
}
