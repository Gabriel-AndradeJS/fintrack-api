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
public class ResponseLoginDTO {
    private String name;
    private String email;
    private String token;

    public ResponseLoginDTO(User user, String token) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.token = token;
    }
}
