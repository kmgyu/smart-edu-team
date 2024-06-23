package com.example.smart_edu_team.post;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;

/**
 * DB의 post 테이블에 연동되는 엔티티입니다.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "필수항목입니다.")
    private String title;
    @NotEmpty(message = "필수항목입니다.")
    private String content;
//  Controller에서 로그인 정보를 가져옴. notempty일 시 템플릿에서 폼데이터를 파싱해 postDTO로 변환시키는 과정에 logical error 발생...
    private String author;
    @NotEmpty(message = "시간을 알 수 없습니다.")
    private LocalDateTime posted_time;
    @NotEmpty(message = "시간을 알 수 없습니다.")
    private LocalDateTime edited_time;
}
