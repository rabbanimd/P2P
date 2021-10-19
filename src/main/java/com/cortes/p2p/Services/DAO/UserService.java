package com.cortes.p2p.Services.DAO;

import com.cortes.p2p.DTO.SignupDTO;
import com.cortes.p2p.Models.Tag;
import com.cortes.p2p.Models.User;

import java.util.List;

public interface UserService {
//    signup service
    public User addNewUser(SignupDTO user);

    User getUser(String roll);

    User getUser(Long id);

    boolean validateLogin(String roll, String hash, User user);

    List<Tag> getUserTags(Long userid);
}
