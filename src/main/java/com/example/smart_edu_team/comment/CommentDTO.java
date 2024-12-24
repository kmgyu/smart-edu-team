package com.example.smart_edu_team.comment;

import com.example.smart_edu_team.post.PostEntity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 댓글의 DTO 입니다.
 * Entity와 달리 Post를 참조하지 않기 때문에, Post의 Id가 추가적으로 요구됩니다.
 * id는 DB에 키생성을 이양합니다.
 * 생성시간, 수정시간의 경우 서비스에서 요청합니다.
 * 부모댓글이 자식 댓글의 정보를 가집니다.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Long id;
    @NotEmpty(message = "필수항목입니다.")
    private String content;
    @NotEmpty(message = "필수항목입니다.")
    private String author;
    private LocalDateTime posted_time;
    private LocalDateTime edited_time;
    private List<CommentDTO> replies;
}
