package com.example.smart_edu_team.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    // jakarta persistance와 data annotation의 id 어노테이션이 다른것에 주의
    @Id
    private String username;
    private String name;
    private String email;
    private String password;
}
