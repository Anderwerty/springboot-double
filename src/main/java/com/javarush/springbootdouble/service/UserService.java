package com.javarush.springbootdouble.service;

import com.javarush.springbootdouble.dto.UserDto;
import com.javarush.springbootdouble.dto.UserRegistrationDto;
import org.springframework.data.domain.Page;

public interface UserService {
    void register(UserRegistrationDto user);

    Page<UserDto> findAll(Integer page, Integer perPage);

}
