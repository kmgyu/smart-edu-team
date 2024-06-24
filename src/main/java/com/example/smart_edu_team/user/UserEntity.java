package com.example.smart_edu_team.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

/**
 * DB의 User테이블과 연결된 클래스입니다.
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class UserEntity {
    // jakarta persistance와 data annotation의 id 어노테이션이 다른것에 주의
    @Id
    private String username;
    private String name;
    private String email;
    private String password;
}
