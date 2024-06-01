package com.example.smart_edu_team.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Setter
@Getter
public class User {
    @Id
    private String id;
    private String username;
    private String name;
    private String email;
    private String password;

    // Getters and Setters
}
