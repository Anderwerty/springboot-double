package com.javarush.springbootdouble.repository;

import com.javarush.springbootdouble.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    List<User> findByFirstname(String firstname);

    Optional<User> findByEmail(String email);
}
