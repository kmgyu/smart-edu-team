package com.example.smart_edu_team.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    // jakarta persistance와 data annotation의 id 어노테이션이 다른것에 주의
    @Id
    @NotEmpty(message = "필수항목입니다.")
    private String username;
    @NotEmpty(message = "필수항목입니다.")
    private String name;
    @NotEmpty(message = "필수항목입니다.")
    private String email;
    @NotEmpty(message = "필수항목입니다.")
    private String password;
}
