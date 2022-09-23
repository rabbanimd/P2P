package com.cortes.p2p.service.impl;

import com.cortes.p2p.data.models.Interest;
import com.cortes.p2p.repo.InterestRepository;
import com.cortes.p2p.service.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterestServiceImpl implements InterestService {
    @Autowired
    private InterestRepository interestRepository;

    @Override
    public Interest createInterest(String interestName) {
        Interest interest = new Interest(interestName.toUpperCase());
        return interestRepository.save(interest);
    }

    @Override
    public Interest getInterest(String interestName) {
        Interest interest;

        interest = interestRepository.findByName(interestName);
        if (interest == null) {
//            interest does not exists
            return createInterest(interestName);
        }
        return interest;
    }
}
