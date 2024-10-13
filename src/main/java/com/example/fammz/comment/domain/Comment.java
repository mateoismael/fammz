package com.example.fammz.comment.domain;

import com.example.fammz.Post.domain.Post;
import com.example.fammz.user.domain.User;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String content;


    private LocalDateTime createdAt;

    @ManyToOne
    private User user;

    @ManyToOne
    private Post post;


}
