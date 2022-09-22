package com.cortes.p2p.service;

import com.cortes.p2p.data.models.Interest;

public interface InterestService {
    Interest createInterest(String interestName);

    Interest getInterest(String interestName);
}
