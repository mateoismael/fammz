package com.example.fammz.post.application;

import com.example.fammz.post.domain.PostService;
import com.example.fammz.post.dto.PostCreateDto;
import com.example.fammz.post.dto.PostResponseDto;
import com.example.fammz.security.UserPrincipal;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@Valid @RequestBody PostCreateDto postCreateDto,
                                                      @AuthenticationPrincipal UserPrincipal currentUser) {
        PostResponseDto createdPost = postService.createPost(postCreateDto, currentUser.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable Long id,
                                                       @AuthenticationPrincipal UserPrincipal currentUser) {
        PostResponseDto post = postService.getPostById(id, currentUser.getId());
        return ResponseEntity.ok(post);
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getAllPosts(@AuthenticationPrincipal UserPrincipal currentUser) {
        List<PostResponseDto> posts = postService.getAllPostsForUser(currentUser.getId());
        return ResponseEntity.ok(posts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDto> updatePost(@PathVariable Long id,
                                                      @Valid @RequestBody PostCreateDto postCreateDto,
                                                      @AuthenticationPrincipal UserPrincipal currentUser) {
        PostResponseDto updatedPost = postService.updatePost(id, postCreateDto, currentUser.getId());
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id,
                                           @AuthenticationPrincipal UserPrincipal currentUser) {
        postService.deletePost(id, currentUser.getId());
        return ResponseEntity.noContent().build();
    }
}