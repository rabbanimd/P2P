package com.cortes.p2p.repo;

import com.cortes.p2p.data.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
