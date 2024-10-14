package com.example.fammz.comment.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.fammz.user.domain.User;
import com.example.fammz.post.domain.Post;
import java.time.ZonedDateTime;

@Entity
@Data
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @NotNull
    private Post post;

    @NotNull
    @Size(min = 1, max = 500)
    private String content;

    @NotNull
    private ZonedDateTime createdAt;
}