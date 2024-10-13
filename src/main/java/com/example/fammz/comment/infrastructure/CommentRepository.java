package com.example.fammz.comment.infrastructure;

import com.example.fammz.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // Métodos personalizados si son necesarios
}