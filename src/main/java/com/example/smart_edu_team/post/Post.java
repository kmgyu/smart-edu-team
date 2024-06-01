package com.example.smart_edu_team.post;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document("Post")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    /**
     * Mongo DB는 object id를 사용하기 때문에 중복성이 '아예' 없습니다.
     * UUID는 추천하지 않지만 자바니까... 일단 사용했습니다.
     */
    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();
    private String title;
    private String content;
    private String author;
}
