package com.example.fammz.comment.domain;

import com.example.fammz.comment.dto.CommentCreateDto;
import com.example.fammz.comment.dto.CommentResponseDto;
import com.example.fammz.comment.infrastructure.CommentRepository;
import com.example.fammz.exception.ForbiddenAccessException;
import com.example.fammz.user.domain.User;
import com.example.fammz.user.infrastructure.UserRepository;
import com.example.fammz.post.domain.Post;
import com.example.fammz.post.infrastructure.PostRepository;
import com.example.fammz.exception.ResourceNotFoundException;
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
    public CommentResponseDto createComment(CommentCreateDto commentCreateDto, Long currentUserId) {
        Comment comment = new Comment();
        User user = userRepository.findById(currentUserId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Post post = postRepository.findById(commentCreateDto.getPostId())
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        comment.setContent(commentCreateDto.getContent());
        comment.setUser(user);
        comment.setPost(post);
        comment.setCreatedAt(ZonedDateTime.now());

        Comment savedComment = commentRepository.save(comment);
        return convertToResponseDto(savedComment);
    }

    public CommentResponseDto getCommentById(Long id, Long currentUserId) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
        if (!comment.getUser().getId().equals(currentUserId)) {
            throw new ForbiddenAccessException("You don't have permission to view this comment");
        }
        return convertToResponseDto(comment);
    }

    public List<CommentResponseDto> getAllCommentsForUser(Long currentUserId) {
        return commentRepository.findByUserId(currentUserId).stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentResponseDto updateComment(Long id, CommentCreateDto commentCreateDto, Long currentUserId) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));

        if (!comment.getUser().getId().equals(currentUserId)) {
            throw new ForbiddenAccessException("You don't have permission to update this comment");
        }

        comment.setContent(commentCreateDto.getContent());

        Comment updatedComment = commentRepository.save(comment);
        return convertToResponseDto(updatedComment);
    }

    @Transactional
    public void deleteComment(Long id, Long currentUserId) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));

        if (!comment.getUser().getId().equals(currentUserId)) {
            throw new ForbiddenAccessException("You don't have permission to delete this comment");
        }

        commentRepository.deleteById(id);
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