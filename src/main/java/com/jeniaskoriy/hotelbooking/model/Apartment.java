package com.jeniaskoriy.hotelbooking.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "apartment")
@Getter
@Setter
public class Apartment implements Comparable<Apartment>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "number", unique = true)
    private Integer number;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "price")
    private Double price;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "apartment_option",
            joinColumns = @JoinColumn(name = "apartment_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id"))
    private List<AdditionalOption> additionalOptions;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "date_id")
    private List<BookedDate> bookedDates;


    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "booked_apartments",
            joinColumns = @JoinColumn(name = "apartment_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

    @Override
    public int compareTo(Apartment o) {
        return this.getCategory().compareTo(o.category);
    }

    public void addBookedDates( LocalDate bookedDate,Integer duration,String username){
        for(int i = 0;i < duration;i++){
            bookedDates.add(new BookedDate(bookedDate, username));
            bookedDate = bookedDate.plusDays(1);
        }
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "id=" + id +
                ", number=" + number +
                ", category=" + category +
                ", price=" + price +
                ", additionalOptions=" + additionalOptions +
                '}';
    }
}
