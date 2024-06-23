package com.example.smart_edu_team.user;

/**
 * User DTO 및 Entity의 매퍼 클래스입니다.
 */
public class UserMapper {
    /**
     * User Entity를 DTO로 변환해 반환합니다.
     * @param userEntity
     * @return UserDTO
     */
    public static UserDTO toDTO(UserEntity userEntity) {
        return UserDTO.builder()
                .username(userEntity.getUsername())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .build();
    }

    /**
     * UserDTO를 Entity로 변환해 반환합니다.
     * @param userDTO
     * @return UserEntity
     */
    public static UserEntity toEntity(UserDTO userDTO) {
        return UserEntity.builder()
                .username(userDTO.getUsername())
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();
    }

}
