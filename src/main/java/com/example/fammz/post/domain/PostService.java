package com.example.fammz.post.domain;

import com.example.fammz.exception.ForbiddenAccessException;
import com.example.fammz.post.dto.PostCreateDto;
import com.example.fammz.post.dto.PostResponseDto;
import com.example.fammz.post.infrastructure.PostRepository;
import com.example.fammz.user.domain.User;
import com.example.fammz.user.infrastructure.UserRepository;
import com.example.fammz.movie.domain.Movie;
import com.example.fammz.movie.infrastructure.MovieRepository;
import com.example.fammz.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MovieRepository movieRepository;

    @Transactional
    public PostResponseDto createPost(PostCreateDto postCreateDto, Long currentUserId) {
        Post post = new Post();
        User user = userRepository.findById(currentUserId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Movie movie = movieRepository.findById(postCreateDto.getMovieId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));

        post.setContent(postCreateDto.getContent());
        post.setUser(user);
        post.setMovie(movie);
        post.setCreatedAt(ZonedDateTime.now());

        Post savedPost = postRepository.save(post);
        return convertToResponseDto(savedPost);
    }

    public PostResponseDto getPostById(Long id, Long currentUserId) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        if (!post.getUser().getId().equals(currentUserId)) {
            throw new ForbiddenAccessException("You don't have permission to view this post");
        }
        return convertToResponseDto(post);
    }

    public List<PostResponseDto> getAllPostsForUser(Long currentUserId) {
        return postRepository.findByUserId(currentUserId).stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public PostResponseDto updatePost(Long id, PostCreateDto postCreateDto, Long currentUserId) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        if (!post.getUser().getId().equals(currentUserId)) {
            throw new ForbiddenAccessException("You don't have permission to update this post");
        }

        Movie movie = movieRepository.findById(postCreateDto.getMovieId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));

        post.setContent(postCreateDto.getContent());
        post.setMovie(movie);

        Post updatedPost = postRepository.save(post);
        return convertToResponseDto(updatedPost);
    }

    @Transactional
    public void deletePost(Long id, Long currentUserId) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        if (!post.getUser().getId().equals(currentUserId)) {
            throw new ForbiddenAccessException("You don't have permission to delete this post");
        }

        postRepository.deleteById(id);
    }

    private PostResponseDto convertToResponseDto(Post post) {
        PostResponseDto dto = new PostResponseDto();
        dto.setId(post.getId());
        dto.setContent(post.getContent());
        dto.setUserId(post.getUser().getId());
        dto.setUserName(post.getUser().getName());
        dto.setMovieId(post.getMovie().getId());
        dto.setMovieTitle(post.getMovie().getTitle());
        dto.setCreatedAt(post.getCreatedAt());
        return dto;
    }
}