package com.fintrack.fintrack_api.DTOs;


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

    public ResponseLoginDTO(LoginDTO loginDTO, String token) {
        this.name = loginDTO.getName();
        this.email = loginDTO.getEmail();
        this.token = token;
    }
}
