package com.example.smart_edu_team.post;

import com.example.smart_edu_team.comment.CommentEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DB의 post 테이블에 연동되는 엔티티입니다.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "필수항목입니다.")
    private String title;
    @NotEmpty(message = "필수항목입니다.")
    @Column(columnDefinition = "TEXT")
    private String content;
//  Controller에서 로그인 정보를 가져옴. notempty일 시 템플릿에서 폼데이터를 파싱해 postDTO로 변환시키는 과정에 logical error 발생...
    private String author;
//  서버 시간 기준으로 생성
    private LocalDateTime posted_time;
//  동일, 서버 시간 기준 생성
    private LocalDateTime edited_time;
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<CommentEntity> commentEntityList;
}
