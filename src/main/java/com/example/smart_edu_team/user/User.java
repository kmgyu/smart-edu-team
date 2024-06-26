package com.example.smart_edu_team.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    // in this case, no annotation needed. it's auto-generated.
    // jpa 리포지토리와 달리 generatedValue 같은 어노테이션이 필요하지 않다. ㅇㅋ?
    @Id
    private ObjectId id;
    @NotEmpty(message = "필수항목입니다.")
    private String username;
    @NotEmpty(message = "필수항목입니다.")
    private String name;
    @NotEmpty(message = "필수항목입니다.")
    private String email;
    @NotEmpty(message = "필수항목입니다.")
    private String password;
}
