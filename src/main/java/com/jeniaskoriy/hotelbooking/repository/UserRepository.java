
package com.jeniaskoriy.hotelbooking.repository;


import com.jeniaskoriy.hotelbooking.model.Apartment;
import com.jeniaskoriy.hotelbooking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

     @Query("select c from User c")
     List<Apartment> getBookedApartments(String name);

     User findByName(String name);
}
