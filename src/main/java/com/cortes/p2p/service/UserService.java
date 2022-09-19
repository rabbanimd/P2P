package com.cortes.p2p.service;

import com.cortes.p2p.data.DTO.UserDTO;
import com.cortes.p2p.data.models.User;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(UserDTO userDTO);

    UserDTO getUser(Long userId);
    void deleteUser(Long userId);

    User getUserById(Long userId);
}
