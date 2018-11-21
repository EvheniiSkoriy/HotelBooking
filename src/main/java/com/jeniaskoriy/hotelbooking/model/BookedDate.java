package com.jeniaskoriy.hotelbooking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "booked_dates")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookedDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "booked_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate bookedDate;

    @Column(name = "user_name")
    private String username;

    public BookedDate(LocalDate bookedDate, String username) {
        this.bookedDate = bookedDate;
        this.username = username;
    }

    @Override
    public String toString() {
        return "BookedDate{" +
                "id=" + id +
                ", bookedDate=" + bookedDate +
                ", username='" + username + '\'' +
                '}';
    }
}
