package com.cortes.p2p.service;

import com.cortes.p2p.data.DTO.PostDTO;

public interface PostService {
    PostDTO createPost(Long userId, PostDTO postDTO);
}
