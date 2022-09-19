package com.cortes.p2p.controllers;

import com.cortes.p2p.data.DTO.UserDTO;
import com.cortes.p2p.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity createNewUser(@Valid @RequestBody UserDTO userDTO) {
        return new ResponseEntity(userService.createUser(userDTO), HttpStatus.CREATED);
    }
}
