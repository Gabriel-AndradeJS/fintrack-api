package com.fintrack.fintrack_api.services;

import com.fintrack.fintrack_api.DTOs.LoginDTO;
import com.fintrack.fintrack_api.DTOs.ResponseLoginDTO;
import com.fintrack.fintrack_api.DTOs.ResponseUserDTO;
import com.fintrack.fintrack_api.config.JwtConfigToken;
import com.fintrack.fintrack_api.entities.User;
import com.fintrack.fintrack_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtConfigToken createJWT;

    public List<ResponseUserDTO> getUsers() {
        return this.userRepository.findAll()
                .stream()
                .map(ResponseUserDTO::new)
                .collect(Collectors.toList());
    }

    public ResponseEntity<ResponseUserDTO> getUserId(Long id) {
       User user = this.userRepository.findById(id).orElseThrow(
               () -> new IllegalArgumentException("User not found")
       );
       return ResponseEntity.ok(new ResponseUserDTO(user));
    }

//    public Page<User> userPaginated(int page, int limit) {
//        if (limit > 15) limit = 15;
//        System.out.println(limit);
//        Pageable pageable = PageRequest.of(page, limit);
//        return this.userRepository.findAll(pageable);
//    }

    public ResponseEntity<String> createUser(User user) {

        boolean userExists = this.userRepository.existsByEmail(user.getEmail());
        if (userExists) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email ja existe!");
        }

        String senhaHash = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(senhaHash);
        user.setBalance(0.00);
        user.setCurrentBalance(0.00);
        this.userRepository.save(user);

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
        ResponseLoginDTO responseDTO = new ResponseLoginDTO(existingUser, token);
        return ResponseEntity.ok(responseDTO);
    }

    public ResponseUserDTO update(Long id, User user) {
        User existingUser = this.userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("User not found")
        );

        if (user.getName() != null) {
            existingUser.setName(user.getName());
        }
        if (user.getPassword() != null) {
            String senhaHash = bCryptPasswordEncoder.encode(user.getPassword());
            existingUser.setPassword(senhaHash);
        }
        if (user.getBalance() != null) {
            existingUser.setBalance(user.getBalance());
        }

         this.userRepository.save(existingUser);
        return new ResponseUserDTO(existingUser);

    }

    public ResponseEntity<Void> deleteUser(Long id) {
        this.userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("User not found")
        );

        this.userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
