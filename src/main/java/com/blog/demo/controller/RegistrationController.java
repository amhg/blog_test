package com.blog.demo.controller;

import com.blog.demo.model.Users;
import com.blog.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegistrationController
{

    private UserService userService;

    @Autowired
    public RegistrationController( UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    private ResponseEntity<?> getUsers(){
        List<Users> users = userService.findAllUsers();

        if(!users.isEmpty()){
            return new ResponseEntity<>(users, HttpStatus.OK);
        }

        return new ResponseEntity<>("No Users Found", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/registration")
    private ResponseEntity<?> registerUser(@RequestBody Users user){

        if(userService.findByEmail( user.getEmail() ).isPresent()){
            return new ResponseEntity<>("There is already a user registered with the email provided", HttpStatus.BAD_REQUEST);
        }

        if(userService.findByUsername( user.getUsername() ).isPresent()){
            return new ResponseEntity<>("There is already a user registered with the username provided",HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

}
