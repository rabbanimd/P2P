package com.cortes.p2p.service;

import com.cortes.p2p.data.models.Interest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestService  {
    Interest createInterest(String interestName);

    Interest getInterest(String interestName);
}
