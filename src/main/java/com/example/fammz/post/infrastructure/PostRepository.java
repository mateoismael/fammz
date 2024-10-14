<<<<<<< HEAD:src/main/java/com/example/fammz/post/infrastructure/PostRepository.java
package com.example.fammz.post.Repository;
=======
package com.example.fammz.Post.infrastructure;
>>>>>>> 618f301e2124279a1822e480b17dc282eb10be18:src/main/java/com/example/fammz/Post/infrastructure/PostRepository.java

import com.example.fammz.Post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}