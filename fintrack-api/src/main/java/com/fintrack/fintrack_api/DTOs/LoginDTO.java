package com.fintrack.fintrack_api.DTOs;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fintrack.fintrack_api.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    private String name;
    private String email;
    private String password;
    private String token;

    public LoginDTO(User existingUser, String token) {
        this.email = existingUser.getEmail();
        this.name = existingUser.getName();
        this.password = existingUser.getPassword();
        this.token = token;

    }

}
