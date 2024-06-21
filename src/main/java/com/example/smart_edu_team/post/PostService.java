package com.example.smart_edu_team.post;

import com.example.smart_edu_team.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(String id) {
        return postRepository.findByPostId(id);
    }

    public Post createPost(Post post, String author) {
        post.setAuthor(author);
        post.setPosted_time(LocalDateTime.now());
        post.setEdited_time(LocalDateTime.now()
        );
        postRepository.save(post);
        return post;
    }

    public Post updatePost(String id, Post postDetails) {
        Optional<Post> post = getPostById(id);
        Post post1;
        if (post.isPresent()) {
            post1 = post.get();
            post1.setTitle(postDetails.getTitle());
            post1.setContent(postDetails.getContent());
            post1.setAuthor(postDetails.getAuthor());
            post1.setEdited_time(LocalDateTime.now());
            postRepository.save(post1);
            return post1;
        }
        return null;
    }

    public boolean deletePost(String id) {
        Optional<Post> post = getPostById(id);
        if (post.isPresent()) {
            postRepository.delete(post.get());
            return true;
        }
        return false;
    }
}