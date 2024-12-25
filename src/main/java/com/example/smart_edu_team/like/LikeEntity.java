package com.example.smart_edu_team.like;

import com.example.smart_edu_team.comment.CommentEntity;
import com.example.smart_edu_team.post.PostEntity;
import com.example.smart_edu_team.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "likes", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "comment_id", "post_id"}))
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private CommentEntity comment;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntity post;

    @Column(name="created_time", nullable = false, updatable = false)
    @Builder.Default()
    private LocalDateTime createdTime = LocalDateTime.now();
}
