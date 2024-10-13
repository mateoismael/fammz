package com.example.fammz.post.infrastructure;

import com.example.fammz.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // Métodos personalizados si son necesarios
}