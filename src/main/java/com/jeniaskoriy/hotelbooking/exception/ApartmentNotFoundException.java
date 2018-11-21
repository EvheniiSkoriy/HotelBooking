package com.jeniaskoriy.hotelbooking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ApartmentNotFoundException extends RuntimeException {

    public ApartmentNotFoundException(Integer apartment) {

        super(apartment + " not found");
    }

}
