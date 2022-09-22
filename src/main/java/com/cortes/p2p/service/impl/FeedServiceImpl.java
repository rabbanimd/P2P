package com.cortes.p2p.service.impl;

import com.cortes.p2p.data.DTO.InterestListDTO;
import com.cortes.p2p.data.models.User;
import com.cortes.p2p.data.payload.FeedPageResponse;
import com.cortes.p2p.service.FeedService;
import com.cortes.p2p.service.PostService;
import com.cortes.p2p.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class FeedServiceImpl implements FeedService {
    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Override
    public FeedPageResponse getFeedsWithInterestList(InterestListDTO interestListDTO) {
        return null;
    }

    @Override
    public FeedPageResponse getFeedsWithUserId(Long userId) {
        User user = userService.getUserById(userId);
        /**
         * case 1 : user has empty interest list
         * case 2 : user's interest has less number of posts
         * @return FeedPageResponse
         */
        return null;
    }
}
