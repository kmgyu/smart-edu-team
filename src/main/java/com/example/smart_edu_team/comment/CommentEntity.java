package com.example.smart_edu_team.comment;

import com.example.smart_edu_team.post.PostEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 댓글 엔티티입니다.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String content;
    @NotEmpty(message = "필수항목입니다.")
    private String author;
    private LocalDateTime posted_time;
    private LocalDateTime edited_time;
    @ManyToOne
    private PostEntity post;
}
