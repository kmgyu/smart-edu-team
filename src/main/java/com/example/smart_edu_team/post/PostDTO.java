package com.example.smart_edu_team.post;

import com.example.smart_edu_team.comment.CommentDTO;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Post에 대한 DTO입니다.
 * id의 경우 Entity에서 DB에 생성권한을 이양하므로 디폴트 초기화값이 존재하지 않습니다.
 * author는 Controller에서 받아온 Principal을 통해 등록됩니다.
 * posted_time, edited_time은 Service에서 서버시간 기준으로 초기화합니다.
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    // Entity에서 DB에 생성권한 이양하므로 디폴트 생성값 없어도 된다.
    private Long id;
    @NotEmpty(message = "필수항목입니다.")
    private String title;
    @NotEmpty(message = "필수항목입니다.")
    private String content;
    //  Controller에서 principal로 로그인 정보를 가져옴. notempty일 시 템플릿에서 폼데이터를 파싱해 postDTO로 변환시키는 과정에 logical error 발생...
    private String author;
    //  서버 시간 기준으로 생성
    private LocalDateTime posted_time;
    //  동일, 서버 시간 기준 생성
    private LocalDateTime edited_time;
}
