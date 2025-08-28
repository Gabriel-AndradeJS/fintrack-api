package com.fintrack.fintrack_api.services;

import com.fintrack.fintrack_api.DTOs.LoginDTO;
import com.fintrack.fintrack_api.DTOs.ResponseLoginDTO;
import com.fintrack.fintrack_api.DTOs.UserDTO;
import com.fintrack.fintrack_api.config.JwtConfigToken;
import com.fintrack.fintrack_api.entities.User;
import com.fintrack.fintrack_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<String> createUser(User user) {

        boolean userExists = this.userRepository.existsByEmail(user.getEmail());
        if (userExists) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email ja existe!");
        }

        String senhaHash = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(senhaHash);
        this.userRepository.save(user);
        UserDTO userDTO = new UserDTO(user);

        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário criado com sucesso.");
    }

    public ResponseEntity<ResponseLoginDTO> login(LoginDTO loginDTO) {

        if (loginDTO.getEmail() == null || loginDTO.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        User existingUser = userRepository.findByEmail(loginDTO.getEmail());
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        boolean passwordMatches = bCryptPasswordEncoder.matches(loginDTO.getPassword(), existingUser.getPassword());
        if (!passwordMatches) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String token = createJWT.generateToken(existingUser.getEmail());
        ResponseLoginDTO responseDTO = new ResponseLoginDTO(loginDTO, token);
        return ResponseEntity.ok(responseDTO);
    }
}
