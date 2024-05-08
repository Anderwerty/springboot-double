package com.javarush.springbootdouble.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(setterPrefix = "with")
public class UserDto {
    private String id;

    private String firstname;

    private String lastname;

    private String email;

}
