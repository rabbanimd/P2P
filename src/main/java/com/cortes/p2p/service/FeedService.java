package com.cortes.p2p.service;

import com.cortes.p2p.data.DTO.InterestListDTO;
import com.cortes.p2p.data.payload.FeedPageResponse;

public interface FeedService {
    FeedPageResponse getFeedsWithInterestList(InterestListDTO interestListDTO);
    FeedPageResponse getFeedsWithUserId(Long userId);
}
