package com.fintrack.fintrack_api.DTOs;

import com.fintrack.fintrack_api.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String name;
    private String email;
    private String token;
    private String createdAt;
    private String updatedAt;

    public UserDTO(User user) {
        this.email = user.getEmail();
        this.name = user.getName();
    }

    public UserDTO(User user, String token) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.token = token;
    }
}
