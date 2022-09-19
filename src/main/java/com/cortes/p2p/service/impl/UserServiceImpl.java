package com.cortes.p2p.service.impl;

import com.cortes.p2p.Resolve.exceptions.ResourceAlreadyExistException;
import com.cortes.p2p.Resolve.exceptions.ResourceNotFoundException;
import com.cortes.p2p.data.DTO.UserDTO;
import com.cortes.p2p.data.models.Interest;
import com.cortes.p2p.data.models.User;
import com.cortes.p2p.repo.UserRepository;
import com.cortes.p2p.service.InterestService;
import com.cortes.p2p.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private InterestService interestService;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user;
        user = userRepository.findByUsername(userDTO.getUsername());
        if(user != null)
            throw new ResourceAlreadyExistException("User", "Username", userDTO.getUsername());

//        persist user
        user = new User();
        user.setUsername(userDTO.getUsername());
        user.setName(userDTO.getName());
        for(String interestName : userDTO.getInterestList()) {
            Interest interest = interestService.getInterest(interestName);
            interest.setTotalUsers(interest.getTotalUsers()+1);
            user.getInterests().add(interest);
        }
        return userToUserDto(userRepository.save(user));
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public UserDTO getUser(Long userId) {
        return null;
    }

    @Override
    public void deleteUser(Long userId) {

    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user","id", userId));
    }

    private UserDTO userToUserDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUsername(user.getUsername());
        userDTO.setName(user.getName());
        userDTO.setInterestList(user.getInterests().stream().map(interest -> interest.getName()).collect(Collectors.toList()));
        return userDTO;
    }
}
