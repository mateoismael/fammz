package com.example.fammz.comment.application;

import com.example.fammz.comment.domain.CommentService;
import com.example.fammz.comment.dto.CommentCreateDto;
import com.example.fammz.comment.dto.CommentResponseDto;
import com.example.fammz.security.UserPrincipal;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(@Valid @RequestBody CommentCreateDto commentCreateDto,
                                                            @AuthenticationPrincipal UserPrincipal currentUser) {
        CommentResponseDto createdComment = commentService.createComment(commentCreateDto, currentUser.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDto> getCommentById(@PathVariable Long id,
                                                             @AuthenticationPrincipal UserPrincipal currentUser) {
        CommentResponseDto comment = commentService.getCommentById(id, currentUser.getId());
        return ResponseEntity.ok(comment);
    }

    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> getAllComments(@AuthenticationPrincipal UserPrincipal currentUser) {
        List<CommentResponseDto> comments = commentService.getAllCommentsForUser(currentUser.getId());
        return ResponseEntity.ok(comments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long id,
                                                            @Valid @RequestBody CommentCreateDto commentCreateDto,
                                                            @AuthenticationPrincipal UserPrincipal currentUser) {
        CommentResponseDto updatedComment = commentService.updateComment(id, commentCreateDto, currentUser.getId());
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id,
                                              @AuthenticationPrincipal UserPrincipal currentUser) {
        commentService.deleteComment(id, currentUser.getId());
        return ResponseEntity.noContent().build();
    }
}