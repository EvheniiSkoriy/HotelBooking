package com.jeniaskoriy.hotelbooking.dto;


import com.jeniaskoriy.hotelbooking.model.AdditionalOption;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class BookingList {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    private Integer roomId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate date;

    @NotNull
    private Integer duration;

    private List<AdditionalOption> additionalOptions;

}
