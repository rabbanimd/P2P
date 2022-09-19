package com.cortes.p2p.service;

import com.cortes.p2p.data.DTO.InterestListDTO;
import com.cortes.p2p.data.DTO.UserDTO;
import com.cortes.p2p.data.models.User;
import org.springframework.http.HttpStatus;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(UserDTO userDTO);

    void deleteUser(Long userId);

    User getUserById(Long userId);

    UserDTO addInterests(Long userId, InterestListDTO interestListDTO);

    UserDTO fetchUser(Long userId);
}
