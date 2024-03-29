package com.cortes.p2p.controllers;

import com.cortes.p2p.data.DTO.LoginDTO;
import com.cortes.p2p.data.DTO.SignupDTO;
import com.cortes.p2p.data.payload.Author;
import com.cortes.p2p.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/auth")
@CrossOrigin
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

    @PostMapping("/login")
    public ResponseEntity loginUser(@Valid @RequestBody LoginDTO loginDTO) {
        Author author = authService.loginUser(loginDTO);
        if (author == null) {
            return new ResponseEntity<>("Login failed", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(author, HttpStatus.OK);
    }

    @GetMapping("/{id}/regenerate")
    public ResponseEntity regenerateUserAuthToken(@PathVariable("id") Long userId) {
        authService.regenerateAuthToken(userId);
        return new ResponseEntity("created. Please check your email.", HttpStatus.CREATED);
    }

    @PostMapping("/{id}/updateEmail")
    public ResponseEntity updateNewUserEmail(@PathVariable("id") Long userId, @RequestParam(
            "email") String newEmail) {
        authService.updateEmail(userId, newEmail);
        return new ResponseEntity("updated. Please verify new email.", HttpStatus.CREATED);
    }
}
