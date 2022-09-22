package com.cortes.p2p.repo;

import com.cortes.p2p.data.models.Interest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestRepository extends JpaRepository<Interest, Long> {

    Interest findByName(String interestName);
}
