package com.javarush.springbootdouble.controller;

import com.javarush.springbootdouble.dto.UserDto;
import com.javarush.springbootdouble.dto.UserRegistrationDto;
import com.javarush.springbootdouble.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService userService;

    @PostMapping("/registration")
    public void register(@RequestBody UserRegistrationDto user) {
        userService.register(user);
    }

    // GET .. /users?page=1&perPage=10
    // GET  /users?page=blabla&perPage=
    @GetMapping("/users")
    public List<UserDto> getAllUsers(@RequestParam(defaultValue = "1", name = "page", required = false) String page,
                                     @RequestParam(defaultValue = "10", name = "perPage", required = false) String perPage) {
        Integer userPage = convert(page, 1);
        Integer userPerPage = convert(perPage, 10);

        Page<UserDto> usersPage = userService.findAll(userPage - 1, userPerPage);

        return usersPage.getContent();
    }

    private static Integer convert(String number, Integer defaultValue) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException | NullPointerException e) {
            return defaultValue;
        }
    }
}
