package com.javarush.springbootdouble.service.validator;

import com.javarush.springbootdouble.dto.UserRegistrationDto;
import com.javarush.springbootdouble.service.exception.RegistrationRuntimeException;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    public void validateUserRegistration(UserRegistrationDto user) {
        if (user == null || !user.getPassword().equals(user.getRepeatedPassword())) {
            throw new RegistrationRuntimeException("password and repeated password should be the same");
        }
        // password pattern
        // email pattern
    }
}
