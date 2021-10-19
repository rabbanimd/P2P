package com.cortes.p2p.Services.Impl;

import com.cortes.p2p.DTO.SignupDTO;
import com.cortes.p2p.Models.Tag;
import com.cortes.p2p.Models.User;
import com.cortes.p2p.Repositories.TagRepository;
import com.cortes.p2p.Repositories.UserRepository;
import com.cortes.p2p.Services.DAO.UserService;
import com.cortes.p2p.utils.KeyHashGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TagRepository tagRepository;

    @Override
    public User addNewUser(SignupDTO user) {
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setUsername(user.getUsername());
        newUser.setHash(KeyHashGenerator.generateHash(user.getHash()));
        newUser.setRoll(user.getRoll());
        newUser.setStudentId(user.getStudentId());
        newUser.setBio(user.getBio());
        newUser.setProfilePic(user.getProfilePic());
        newUser.setLink(user.getLink());

        for(String x:user.getFtags())
        {
            Tag tag = tagRepository.findByName(x.toUpperCase());
            if(tag == null)
            {
                tag = new Tag();
                tag.setName(x.toUpperCase());
                tagRepository.save(tag);
            }
            tag.getUsers().add(newUser);
            tag.incrementHits();
            newUser.getTags().add(tag);
        }

        return userRepository.save(newUser);
    }

    @Override
    public User getUser(String roll) {
        return userRepository.findByRoll(roll);
    }

    @Override
    public User getUser(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public boolean validateLogin(String roll, String hash, User user) {
        if(user.getHash().equals(KeyHashGenerator.generateHash(hash)))
        {
            return true;
        }
        return false;
    }

    @Override
    public List<Tag> getUserTags(Long userid) {
        return null;
    }
}
