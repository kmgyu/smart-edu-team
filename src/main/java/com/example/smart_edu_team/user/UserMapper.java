package com.example.smart_edu_team.user;

public class UserMapper {
    public static UserDTO toDTO(UserEntity userEntity) {
        return UserDTO.builder()
                .username(userEntity.getUsername())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .build();
    }

    public static UserEntity toEntity(UserDTO userDTO) {
        return UserEntity.builder()
                .username(userDTO.getUsername())
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();
    }

}
