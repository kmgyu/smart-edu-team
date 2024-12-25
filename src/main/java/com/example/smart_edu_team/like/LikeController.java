package com.example.smart_edu_team.like;

import com.example.smart_edu_team.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    /**
     * 게시글에 대해 좋아요를 토글합니다.
     * 사용자가 이미 좋아요를 누른 경우 좋아요를 취소하고, 그렇지 않은 경우 좋아요를 추가합니다.
     * @param postId 좋아요를 누를 게시글의 ID
     * @param principal 현재 로그인한 사용자의 정보를 제공하는 Principal 객체
     * @return "liked" 또는 "unliked" 문자열을 포함한 ResponseEntity 객체
     */
    @PostMapping("/posts/{postId}")
    public ResponseEntity<String> toggleLikeOnPost(@PathVariable Long postId, Principal principal) {
        boolean isLiked = likeService.toggleLikeOnPost(principal.getName(), postId);
        return ResponseEntity.ok(isLiked ? "liked" : "unliked");
    }

    /**
     * 댓글에 대해 좋아요를 토글합니다.
     * 사용자가 이미 좋아요를 누른 경우 좋아요를 취소하고, 그렇지 않은 경우 좋아요를 추가합니다.
     * @param commentId 좋아요를 누를 댓글의 ID
     * @param principal 현재 로그인한 사용자의 정보를 제공하는 Principal 객체
     * @return "liked" 또는 "unliked" 문자열을 포함한 ResponseEntity 객체
     */
    @PostMapping("/comments/{commentId}")
    public ResponseEntity<String> toggleLikeOnComment(@PathVariable Long commentId, Principal principal) {
        boolean isLiked = likeService.toggleLikeOnComment(principal.getName(), commentId);
        return ResponseEntity.ok(isLiked ? "liked" : "unliked");
    }
}
