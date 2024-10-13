package com.example.fammz.comment.domain;

import com.example.fammz.comment.dto.CommentCreateDto;
import com.example.fammz.comment.dto.CommentResponseDto;
import com.example.fammz.comment.infrastructure.CommentRepository;
import com.example.fammz.user.domain.User;
import com.example.fammz.user.infrastructure.UserRepository;
import com.example.fammz.post.domain.Post;
import com.example.fammz.post.infrastructure.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @Transactional
    public CommentResponseDto createComment(CommentCreateDto commentCreateDto) {
        Comment comment = new Comment();
        updateCommentFromDto(comment, commentCreateDto);
        comment.setCreatedAt(ZonedDateTime.now());
        Comment savedComment = commentRepository.save(comment);
        return convertToResponseDto(savedComment);
    }

    public CommentResponseDto getCommentById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        return convertToResponseDto(comment);
    }

    public List<CommentResponseDto> getAllComments() {
        return commentRepository.findAll().stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentResponseDto updateComment(Long id, CommentCreateDto commentCreateDto) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        updateCommentFromDto(comment, commentCreateDto);
        Comment updatedComment = commentRepository.save(comment);
        return convertToResponseDto(updatedComment);
    }

    @Transactional
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    private void updateCommentFromDto(Comment comment, CommentCreateDto dto) {
        comment.setContent(dto.getContent());
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        comment.setUser(user);
        Post post = postRepository.findById(dto.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found"));
        comment.setPost(post);
    }

    private CommentResponseDto convertToResponseDto(Comment comment) {
        CommentResponseDto dto = new CommentResponseDto();
        dto.setId(comment.getId());
        dto.setContent(comment.getContent());
        dto.setUserId(comment.getUser().getId());
        dto.setUserName(comment.getUser().getName());
        dto.setPostId(comment.getPost().getId());
        dto.setCreatedAt(comment.getCreatedAt());
        return dto;
    }
}