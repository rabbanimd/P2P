package com.cortes.p2p.service.impl;

import com.cortes.p2p.Resolve.exceptions.ResourceAlreadyExistException;
import com.cortes.p2p.Resolve.exceptions.ResourceNotFoundException;
import com.cortes.p2p.data.DTO.InterestListDTO;
import com.cortes.p2p.data.DTO.UserDTO;
import com.cortes.p2p.data.models.Interest;
import com.cortes.p2p.data.models.User;
import com.cortes.p2p.data.payload.Author;
import com.cortes.p2p.repo.UserRepository;
import com.cortes.p2p.service.InterestService;
import com.cortes.p2p.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private InterestService interestService;

    @Override
    public Author createUser(UserDTO userDTO) {
        User user;
        user = userRepository.findByUsername(userDTO.getUsername());
        if (user != null)
            throw new ResourceAlreadyExistException("User", "Username", userDTO.getUsername());

//        persist user
        user = new User();
        user.setUsername(userDTO.getUsername());
        user.setName(userDTO.getName());
        for (String interestName : userDTO.getInterestList()) {
            Interest interest = interestService.getInterest(interestName);
            interest.incrementTotalUsers();
            user.getInterests().add(interest);
        }
        return Mapper.userToAuthor(userRepository.save(user));
    }

    @Override
    public Author updateUser(UserDTO userDTO) {
        return null;
    }


    @Override
    public void deleteUser(Long userId) {

    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
    }

    @Override
    public Author addInterests(Long userId, InterestListDTO interestListDTO) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
        for (String interestName : interestListDTO.getInterests()) {
            Interest interest = interestService.getInterest(interestName.toUpperCase());
            if (user.getInterests().contains(interest)) {
                // already exists
                continue;
            }
            user.getInterests().add(interest);
            interest.incrementTotalUsers();
        }
        return Mapper.userToAuthor(userRepository.save(user));
    }

    @Override
    public Author fetchUser(Long userId) {
        return Mapper.userToAuthor(getUserById(userId));
    }


}
