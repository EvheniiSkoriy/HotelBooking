package com.jeniaskoriy.hotelbooking.dto;

import com.jeniaskoriy.hotelbooking.model.BookedDate;
import com.jeniaskoriy.hotelbooking.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApartmentDTOOut {

    private Integer id;

    private Integer number;

    private Category category;

    private Double price;

    private LocalDate bookedDate;

    public ApartmentDTOOut(Integer id, Integer number, Category category, Double price) {
        this.id = id;
        this.number = number;
        this.category = category;
        this.price = price;
    }


}
