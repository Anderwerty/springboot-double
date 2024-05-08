package com.javarush.springbootdouble.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserRegistrationDto {

    private String email;

    private String password;

    private String repeatedPassword;


}
