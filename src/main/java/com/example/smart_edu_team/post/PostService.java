package com.example.smart_edu_team.post;

import com.example.smart_edu_team.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(String id) {
        return postRepository.findByPostId(id);
    }

    public Post createPost(Post post, String author) {
        post.setAuthor(author);
        postRepository.save(post);
        return post;
    }

    public Post updatePost(String id, Post postDetails) {
        Post post = getPostById(id);
        if (post != null) {
            post.setTitle(postDetails.getTitle());
            post.setContent(postDetails.getContent());
            post.setAuthor(postDetails.getAuthor());
            return post;
        }
        return null;
    }

    public boolean deletePost(String id) {
        Post post = getPostById(id);
        if (post != null) {
            postRepository.delete(post);
            return true;
        }
        return false;
    }
}