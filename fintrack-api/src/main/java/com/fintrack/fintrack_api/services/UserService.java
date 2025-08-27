package com.fintrack.fintrack_api.services;

import com.fintrack.fintrack_api.DTOs.UserDTO;
import com.fintrack.fintrack_api.config.JwtConfigToken;
import com.fintrack.fintrack_api.entities.User;
import com.fintrack.fintrack_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtConfigToken createJWT;

    public String createUser(UserDTO userDTO) {
        String senhaHash = bCryptPasswordEncoder.encode(userDTO.getPassword());
        String token = createJWT.generateToken(userDTO.getEmail());
        userDTO.setPassword(senhaHash);
//        User userSave = new User(userDTO);
//        this.userRepository.save(userSave);

        return token;
    }
}
