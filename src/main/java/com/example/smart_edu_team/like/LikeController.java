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

    @PostMapping("/posts/{postId}")
    public ResponseEntity<String> toggleLikeOnPost(@PathVariable Long postId, Principal principal) {
        boolean isLiked = likeService.toggleLikeOnPost(principal.getName(), postId);
        return ResponseEntity.ok(isLiked ? "liked" : "unliked");
    }

    @PostMapping("/comments/{commentId}")
    public ResponseEntity<String> toggleLikeOnComment(@PathVariable Long commentId, Principal principal) {
        boolean isLiked = likeService.toggleLikeOnComment(principal.getName(), commentId);
        return ResponseEntity.ok(isLiked ? "liked" : "unliked");
    }
}
