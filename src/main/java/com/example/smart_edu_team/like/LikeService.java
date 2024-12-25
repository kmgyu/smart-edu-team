package com.example.smart_edu_team.like;

import com.example.smart_edu_team.comment.CommentEntity;
import com.example.smart_edu_team.comment.CommentRepository;
import com.example.smart_edu_team.notification.NotificationService;
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
    private final NotificationService notificationService;

    /**
     * 게시글에 대해 좋아요를 토글합니다.
     * 사용자가 이미 좋아요를 누른 경우 좋아요를 취소하고, 그렇지 않은 경우 좋아요를 추가합니다.
     * 좋아요를 추가할 경우 게시글 작성자에게 알림을 보냅니다.
     *
     * @param username 좋아요를 누르는 사용자의 아이디
     * @param postId 좋아요를 누를 게시글의 ID
     * @return true(좋아요 추가), false(좋아요 취소)
     */
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
            notificationService.createNotification(
                    post.get().getAuthor(),
                    String.format("%s님이 게시글 '%s'에 좋아요를 눌렀습니다.", username, post.get().getTitle())
            );
            return true; // 좋아요 추가
        }
    }

    /**
     * 댓글에 대해 좋아요를 토글합니다.
     * 사용자가 이미 좋아요를 누른 경우 좋아요를 취소하고, 그렇지 않은 경우 좋아요를 추가합니다.
     *
     * @param username 좋아요를 누르는 사용자의 아이디
     * @param commentId 좋아요를 누를 댓글의 ID
     * @return true(좋아요 추가), false(좋아요 취소)
     */
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
