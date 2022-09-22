package com.cortes.p2p.controllers;

import com.cortes.p2p.data.DTO.SignupDTO;
import com.cortes.p2p.service.AuthService;
import com.cortes.p2p.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity createUser(@Valid @RequestBody SignupDTO signupDTO) {
        return new ResponseEntity(authService.registerUser(signupDTO), HttpStatus.CREATED);
    }

    @PutMapping("/authenticate/{token}")
    public ResponseEntity authorizeUser(@PathVariable("token") String userToken) {
        return new ResponseEntity(authService.verifyToken(userToken));
    }
}
