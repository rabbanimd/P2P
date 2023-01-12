package com.cortes.p2p.service.impl;

import com.cortes.p2p.data.DTO.PostDTO;
import com.cortes.p2p.data.models.Post;
import com.cortes.p2p.data.models.User;
import com.cortes.p2p.data.payload.Author;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class Mapper {
    protected static Author userToAuthor(User user) {
        Author author = new Author(user.getUserId(),
                user.getName(),
                user.getUsername(),
                user.getEmail(),
                user.isAuthorized()
        );
        author.setInterestList(user.getInterests().stream().map(interest -> interest.getName()).collect(Collectors.toList()));
        return author;
    }

    protected static PostDTO postToPostDTO(Post post, User user) {
        PostDTO postDTO = new PostDTO();
        postDTO.setPostId(post.getPostId());
        postDTO.setDescription(post.getDescription());
        postDTO.setImageLink(post.getImageLink());
        postDTO.setPostStatus(post.getPostStatus());
        postDTO.setAuthor(userToAuthor(user));
        postDTO.setInterestList(post.getInterests().stream().map(interest -> interest.getName()).collect(Collectors.toList()));
        return postDTO;
    }
}
