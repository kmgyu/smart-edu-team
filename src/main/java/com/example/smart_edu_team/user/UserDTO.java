package com.example.smart_edu_team.user;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserDTO 클래스입니다.
 * username (id) 의 경우 보안을 위해 최소 5글자, 최대 17글자입니다.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @Size(min = 5, max = 17, message = "최소 5글자 최대 17글자입니다.")
    @NotEmpty(message = "필수항목입니다.")
    private String username;
    @NotEmpty(message = "필수항목입니다.")
    private String name;
    @NotEmpty(message = "필수항목입니다.")
    private String email;
    @NotEmpty(message = "필수항목입니다.")
    private String password;
}
