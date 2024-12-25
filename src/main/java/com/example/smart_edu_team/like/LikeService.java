package com.example.smart_edu_team.like;

import com.example.smart_edu_team.comment.CommentEntity;
import com.example.smart_edu_team.comment.CommentRepository;
import com.example.smart_edu_team.post.PostEntity;
import com.example.smart_edu_team.post.PostRepository;
import com.example.smart_edu_team.user.UserEntity;
import com.example.smart_edu_team.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public boolean toggleLikeOnPost(String username, Long postId) {
        Optional<UserEntity> user = userRepository.findById(username);
        Optional<PostEntity> post = postRepository.findById(postId);

        if (user.isEmpty() || post.isEmpty()) {
            throw new IllegalArgumentException("Invalid user or post");
        }

        Optional<LikeEntity> existingLike = likeRepository.findByUserAndPost(user.get(), post.get());
        if (existingLike.isPresent()) {
            likeRepository.delete(existingLike.get());
            return false; // 좋아요 취소
        } else {
            LikeEntity newLike = LikeEntity.builder()
                    .user(user.get())
                    .post(post.get())
                    .build();
            likeRepository.save(newLike);
            return true; // 좋아요 추가
        }
    }

    public boolean toggleLikeOnComment(String username, Long commentId) {
        Optional<UserEntity> user = userRepository.findById(username);
        Optional<CommentEntity> comment = commentRepository.findById(commentId);

        if (user.isEmpty() || comment.isEmpty()) {
            throw new IllegalArgumentException("Invalid user or comment");
        }

        Optional<LikeEntity> existingLike = likeRepository.findByUserAndComment(user.get(), comment.get());
        if (existingLike.isPresent()) {
            likeRepository.delete(existingLike.get());
            return false; // 좋아요 취소
        } else {
            LikeEntity newLike = LikeEntity.builder()
                    .user(user.get())
                    .comment(comment.get())
                    .build();
            likeRepository.save(newLike);
            return true; // 좋아요 추가
        }
    }
}
