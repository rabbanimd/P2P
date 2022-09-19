package com.cortes.p2p.controllers;

import com.cortes.p2p.data.DTO.PostDTO;
import com.cortes.p2p.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/{id}/create")
    public ResponseEntity createPost(@PathVariable("id") Long userId,@Valid @RequestBody PostDTO postDTO) {
        return new ResponseEntity(postService.createPost(userId, postDTO), HttpStatus.CREATED);
    }
}
