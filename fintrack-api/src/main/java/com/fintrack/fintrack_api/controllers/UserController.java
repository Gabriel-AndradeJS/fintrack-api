package com.fintrack.fintrack_api.controllers;

import com.fintrack.fintrack_api.DTOs.UserDTO;
import com.fintrack.fintrack_api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public String getUsers(@RequestBody UserDTO userDTO) {
        return this.userService.createUser(userDTO);
    }
}
