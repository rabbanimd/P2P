package com.cortes.p2p.repo;

import com.cortes.p2p.data.models.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<UserCredentials, Long> {
    UserCredentials findByAuthToken(String userToken);
}
