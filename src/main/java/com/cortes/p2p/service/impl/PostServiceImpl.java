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
        post.setAuthor(user);
        for(String interestName : postDTO.getInterestList()) {
            Interest interest = interestService.getInterest(interestName);
            interest.setTotalPosts(interest.getTotalPosts() + 1);
            post.getInterests().add(interest);
        }
        user.getPosts().add(post);
        userRepository.save(user);
        return postToPostDto(postRepository.save(post));
    }

    private PostDTO postToPostDto(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setPostId(post.getPostId());
        postDTO.setDescription(post.getDescription());
        postDTO.setImageLink(post.getImageLink());
        postDTO.setInterestList(post.getInterests().stream().map(interest -> interest.getName()).collect(Collectors.toList()));
        return postDTO;
    }
}
