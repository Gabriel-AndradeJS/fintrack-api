package com.fintrack.fintrack_api.controllers;

import com.fintrack.fintrack_api.DTOs.LoginDTO;
import com.fintrack.fintrack_api.DTOs.UserDTO;
import com.fintrack.fintrack_api.entities.User;
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
    public ResponseEntity<String> createUser(@RequestBody User user) {
        return this.userService.createUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDTO> login(@RequestBody LoginDTO loginDTO) {
        return this.userService.login(loginDTO);
    }
}
