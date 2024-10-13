package com.example.fammz.post.domain;

import com.example.fammz.post.dto.PostCreateDto;
import com.example.fammz.post.dto.PostResponseDto;
import com.example.fammz.post.infrastructure.PostRepository;
import com.example.fammz.user.domain.User;
import com.example.fammz.user.infrastructure.UserRepository;
import com.example.fammz.movie.domain.Movie;
import com.example.fammz.movie.infrastructure.MovieRepository;
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
    public PostResponseDto createPost(PostCreateDto postCreateDto) {
        Post post = new Post();
        updatePostFromDto(post, postCreateDto);
        post.setCreatedAt(ZonedDateTime.now());
        Post savedPost = postRepository.save(post);
        return convertToResponseDto(savedPost);
    }

    public PostResponseDto getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        return convertToResponseDto(post);
    }

    public List<PostResponseDto> getAllPosts() {
        return postRepository.findAll().stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public PostResponseDto updatePost(Long id, PostCreateDto postCreateDto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        updatePostFromDto(post, postCreateDto);
        Post updatedPost = postRepository.save(post);
        return convertToResponseDto(updatedPost);
    }

    @Transactional
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    private void updatePostFromDto(Post post, PostCreateDto dto) {
        post.setContent(dto.getContent());
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        post.setUser(user);
        Movie movie = movieRepository.findById(dto.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        post.setMovie(movie);
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