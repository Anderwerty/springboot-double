package com.javarush.springbootdouble.controller;

import com.javarush.springbootdouble.dto.UserDto;
import com.javarush.springbootdouble.dto.UserRegistrationDto;
import com.javarush.springbootdouble.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService userService;

    // User(s) ---> URI /users, HTTP methods(POST, GET, DELETE, PUT)
    // Account(s) --> URI /accounts ....
    //-------------------------------

    // Richardson Maturity Model https://restfulapi.net/richardson-maturity-model/
    // level - 0
    // Just one URI and one HTTP method(POST)
    // Single URI (/bank), one  HTTP(POST) method, body(typeOfEntity, action, object)
    // typeOfEntity (USER, ACCOUNT)
    // action(CREATE, READ, UPDATE, DELETE)
    //-----------------------------------------

    // level - 1
    // Multiple URI and one HTTP method(POST)
    // multiple URIs (/users, /accounts, ... ), one HTTP(POST) method , body (action, object)
    //
    // action(CREATE, READ, UPDATE, DELETE)
    //-------------------------------------------

    // level - 2
    // Multiple URI and multiple HTTP methods(GET, PUT, POST, DELETE, ....)
    // multiple URIs (/users, /accounts, ... ), body (object)
    //
    //--------------------------------------------

    // level - 3 (level - 2 + HATEOAS)
    // Multiple URI and multiple HTTP methods(GET, PUT, POST, DELETE, ....)
    // User(account  --> url account)




    @PostMapping("/users")
    public void register(@RequestBody UserRegistrationDto user) {
        userService.register(user);
    }

    // GET .. /users?page=1&perPage=10
    // GET  /users?page=blabla&perPage=
    private  Integer userPage;
    @GetMapping("/users")
    public List<UserDto> getAllUsers(@RequestParam(defaultValue = "1", name = "page", required = false) String page,
                                     @RequestParam(defaultValue = "10", name = "perPage", required = false) String perPage) {
//        Integer userPage = convert(page, 1);
         userPage = convert(page, 1);
        Integer userPerPage = convert(perPage, 10);

        Page<UserDto> usersPage = userService.findAll(userPage - 1, userPerPage);

        return usersPage.getContent();
    }

    @GetMapping("/users/{userId}")
    public UserDto getUserById(@PathVariable("userId") String id){
       return userService.findById(id);
    }

    @DeleteMapping("/users/{userId}")
    public UserDto deleteById(@PathVariable String userId){
        return userService.deleteById(userId);
    }

    @PutMapping("/users/{userId}")
    public UserDto updateUser(@PathVariable String userId, UserDto userDto){
        return null;
    }

    private static Integer convert(String number, Integer defaultValue) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException | NullPointerException e) {
            return defaultValue;
        }
    }
}
