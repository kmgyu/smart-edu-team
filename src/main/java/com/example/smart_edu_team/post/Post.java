package com.example.smart_edu_team.post;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;

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
    @NotEmpty(message = "필수항목입니다.")
    private String author;
    @NotEmpty(message = "시간을 알 수 없습니다.")
    private LocalDateTime posted_time;
    @NotEmpty(message = "시간을 알 수 없습니다.")
    private LocalDateTime edited_time;
}
