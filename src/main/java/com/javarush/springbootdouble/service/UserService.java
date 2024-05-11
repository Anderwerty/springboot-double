package com.javarush.springbootdouble.service;

import com.javarush.springbootdouble.dto.UserDto;
import com.javarush.springbootdouble.dto.UserRegistrationDto;
import com.javarush.springbootdouble.service.exception.EntityNotFoundRuntimeException;
import org.springframework.data.domain.Page;

/**
 *
 */
public interface UserService {
    void register(UserRegistrationDto user);

    Page<UserDto> findAll(Integer page, Integer perPage);

    UserDto findById(String id) throws EntityNotFoundRuntimeException;

    //
    UserDto deleteById(String userId) throws EntityNotFoundRuntimeException;
}
