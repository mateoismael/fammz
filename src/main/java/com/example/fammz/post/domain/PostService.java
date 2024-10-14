package com.example.fammz.Post.domain;

<<<<<<< HEAD:src/main/java/com/example/fammz/post/domain/PostService.java
import com.example.fammz.post.Repository.PostRepository;
=======
import com.example.fammz.Post.infrastructure.PostRepository;
>>>>>>> 618f301e2124279a1822e480b17dc282eb10be18:src/main/java/com/example/fammz/Post/domain/PostService.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // Crear un nuevo post
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    // Obtener un post por ID
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    // Obtener todos los posts
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // Actualizar un post
    public Post updatePost(Post post) {
        return postRepository.save(post);
    }

    // Eliminar un post
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
