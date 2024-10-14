package com.example.fammz;

<<<<<<< HEAD
import com.example.fammz.post.domain.Post;
import com.example.fammz.post.Controller.PostController;
import com.example.fammz.post.DTOs.PostDTO;
import com.example.fammz.post.domain.PostService;
=======
import com.example.fammz.Post.domain.Post;
import com.example.fammz.Post.application.PostController;
import com.example.fammz.Post.dto.PostDTO;
import com.example.fammz.Post.domain.PostService;
>>>>>>> 618f301e2124279a1822e480b17dc282eb10be18
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PostControllerTest {

    @Mock
    private PostService postService;

    @InjectMocks
    private PostController postController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllPosts() {
        // Crear datos de prueba
        List<Post> posts = new ArrayList<>();
        posts.add(new Post());


        when(postService.getAllPosts()).thenReturn(posts);


        ResponseEntity<List<Post>> response = postController.getAllPosts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    public void testGetPostById() {
        // Crear un post de prueba
        Post post = new Post();
        post.setId(1L);

        // Configurar el mock
        when(postService.getPostById(1L)).thenReturn(Optional.of(post));

        // Llamar al método del controlador
        ResponseEntity<Post> response = postController.getPostById(1L);

        // Verificar el resultado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    public void testCreatePost() {
        // Crear un post de prueba
        Post post = new Post();
        PostDTO postDTO = new PostDTO(1L, 1L, 1L, "Test Content", 0, 0);

        // Configurar el mock
        when(postService.createPost(any(Post.class))).thenReturn(post);

        // Llamar al método del controlador
        ResponseEntity<Post> response = postController.createPost(postDTO);

        // Verificar el resultado
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testDeletePost() {
        // Configurar el mock
        when(postService.getPostById(1L)).thenReturn(Optional.of(new Post()));

        // Llamar al método del controlador
        ResponseEntity<Void> response = postController.deletePost(1L);

        // Verificar el resultado
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
