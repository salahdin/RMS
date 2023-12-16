package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.dto.ResponseDto;
import edu.miu.cs.cs544.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/ping")
    public ResponseEntity<ResponseDto> ping() {
        return new ResponseEntity<>(
                new ResponseDto(true, "Pong", null),
                HttpStatus.OK
        );
    }

}
