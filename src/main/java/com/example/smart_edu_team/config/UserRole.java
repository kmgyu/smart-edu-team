package com.example.smart_edu_team.config;

import lombok.Getter;

/**
 * 유저 권한에 대한 configuration 클래스입니다.
 * 권한 추가 시 해당 클래스에 추가됩니다.
 */
@Getter
public enum UserRole {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private String value;

    /**
     * UserRole을 설정합니다.
     * @param value 설정할 권한의 값입니다.
     */
    UserRole(String value) {
        this.value = value;
    }
}
