package com.cortes.p2p.controllers;

import com.cortes.p2p.data.DTO.InterestListDTO;
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

    @PutMapping("/{id}/addInterest")
    public ResponseEntity addInterest(@PathVariable("id") Long userId, @RequestBody InterestListDTO interestListDTO) {
        return new ResponseEntity(userService.addInterests(userId, interestListDTO), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable("id") Long userId) {
        return new ResponseEntity(userService.fetchUser(userId), HttpStatus.OK);
    }
}
