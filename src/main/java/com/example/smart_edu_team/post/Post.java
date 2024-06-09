package com.example.smart_edu_team.post;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document("Post")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    private String id;
    @NotEmpty(message = "필수항목입니다.")
    private String title;
    @NotEmpty(message = "필수항목입니다.")
    private String content;
    @NotEmpty(message = "필수항목입니다.")
    private String author;
    @NotEmpty(message = "시간을 알 수 없습니다.")
    private LocalDateTime posted_time;
    @NotEmpty(message = "시간을 알 수 없습니다.")
    private LocalDateTime edited_time;
}
