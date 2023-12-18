package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.domain.User;
import edu.miu.cs.cs544.dto.AuthLoginDTO;
import edu.miu.cs.cs544.dto.ResponseDto;
import edu.miu.cs.cs544.security.JwtService;
import edu.miu.cs.cs544.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthLoginDTO authLoginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authLoginDTO.getUsername(),
                        authLoginDTO.getPassword()
                ));
        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(authLoginDTO.getUsername());
            return new ResponseEntity<>(
                    new ResponseDto(true, "Token generated successfully", token),
                    HttpStatus.OK
            );
        } else {
            throw new UsernameNotFoundException("User not found!");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        user.setUserPass(passwordEncoder.encode(user.getUserPass()));
        userService.createUser(user);
        return new ResponseEntity<>("Created User successfully!", HttpStatus.CREATED);
    }

    @GetMapping("/ping")
    public ResponseEntity<ResponseDto> ping() {
        return new ResponseEntity<>(
                new ResponseDto(true, "Pong", null),
                HttpStatus.OK
        );
    }

    @GetMapping("/admin")
    public ResponseEntity<ResponseDto> admin() {
        return new ResponseEntity<>(
                new ResponseDto(true, "I know you are an admin", null),
                HttpStatus.OK
        );
    }

    @GetMapping("/client")
    public ResponseEntity<ResponseDto> client() {
        return new ResponseEntity<>(
                new ResponseDto(true, "I know you are a client", null),
                HttpStatus.OK
        );
    }




}
