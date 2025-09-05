package com.fintrack.fintrack_api.controllers;

import com.fintrack.fintrack_api.DTOs.LoginDTO;
import com.fintrack.fintrack_api.DTOs.ResponseLoginDTO;
import com.fintrack.fintrack_api.DTOs.ResponseUserDTO;
import com.fintrack.fintrack_api.DTOs.UserDTO;
import com.fintrack.fintrack_api.entities.User;
import com.fintrack.fintrack_api.services.UserService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public List<ResponseUserDTO> getUser() {
        return this.userService.getUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseUserDTO> getUserById(@PathVariable Long id) {
        return this.userService.getUserId(id);
    }

    @GetMapping(value = "/{page}/{limit}")
    public ResponseEntity<List<ResponseUserDTO>> getUsersPaginated(@PathVariable int page, @PathVariable int limit) {
        return this.userService.userPaginated(page, limit);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
        return this.userService.createUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseLoginDTO> login(@RequestBody LoginDTO loginDTO) {
        return this.userService.login(loginDTO);
    }

    @PutMapping("/{id}")
    public ResponseUserDTO updateUser(@PathVariable Long id, @RequestBody User user) {
       return this.userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
       return this.userService.deleteUser(id);
    }
}
