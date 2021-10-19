package com.cortes.p2p.Repositories;

import com.cortes.p2p.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByRoll(String roll);
}
