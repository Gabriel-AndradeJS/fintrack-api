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
    private String email;
    private String password;

}
