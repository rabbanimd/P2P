package com.cortes.p2p.service.impl;

import com.cortes.p2p.data.DTO.PostDTO;
import com.cortes.p2p.data.models.Interest;
import com.cortes.p2p.data.models.Post;
import com.cortes.p2p.data.models.User;
import com.cortes.p2p.repo.PostRepository;
import com.cortes.p2p.repo.UserRepository;
import com.cortes.p2p.service.InterestService;
import com.cortes.p2p.service.PostService;
import com.cortes.p2p.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private InterestService interestService;

    @Autowired
    private UserService userService;

    @Override
    public PostDTO createPost(Long userId, PostDTO postDTO) {
        User user = userService.getUserById(userId);
        Post post = new Post();
        post.setDescription(postDTO.getDescription());
        post.setImageLink(postDTO.getImageLink());
        post.setPostStatus(postDTO.getPostStatus());
        post.setAuthor(user);
        if(postDTO.getInterestList().size() == 0) {
            /**
             * post is sent to be created without interest list
             * post interest = user interest
             */
            for(Interest interest : user.getInterests()) {
                interest.incrementTotalPosts();
                post.getInterests().add(interest);
            }
        }else {
            for(String interestName : postDTO.getInterestList()) {
                Interest interest = interestService.getInterest(interestName);
                interest.incrementTotalPosts();
                post.getInterests().add(interest);
            }
        }
        user.getPosts().add(post);
        userRepository.save(user);
        return Mapper.postToPostDTO(postRepository.save(post), user);
    }
}
