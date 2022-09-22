package com.cortes.p2p.service;

import com.cortes.p2p.data.DTO.InterestListDTO;
import com.cortes.p2p.data.DTO.UserDTO;
import com.cortes.p2p.data.models.User;
import com.cortes.p2p.data.payload.Author;

public interface UserService {
    Author updateUser(UserDTO userDTO);

    void deleteUser(Long userId);

    User getUserById(Long userId);

    Author addInterests(Long userId, InterestListDTO interestListDTO);

    Author fetchUser(Long userId);
}
