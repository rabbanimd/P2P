package com.cortes.p2p.service;

import com.cortes.p2p.data.DTO.SignupDTO;
import com.cortes.p2p.data.payload.Author;
import org.springframework.http.HttpStatus;

public interface AuthService {
    Author registerUser(SignupDTO signupDTO);

    HttpStatus verifyToken(String userToken);

    Author loginUser(String username, String password);
}
