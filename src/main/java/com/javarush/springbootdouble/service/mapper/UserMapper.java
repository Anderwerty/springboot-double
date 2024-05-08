package com.javarush.springbootdouble.service.mapper;

import com.javarush.springbootdouble.dto.UserDto;
import com.javarush.springbootdouble.dto.UserRegistrationDto;
import com.javarush.springbootdouble.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapUserRegistrationDtoToUser(UserRegistrationDto userDto){
        if(userDto == null){
            return null;
        }
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        return user;
    }

    public UserDto mapUserToUserDto(User user){
        if(user == null){
            return null;
        }
        return UserDto.builder()
                .withId(user.getId())
                .withFirstname(user.getFirstname())
                .withLastname(user.getLastname())
                .withEmail(user.getEmail())
                .build();
    }
}
