package com.example.smart_edu_team.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 사용자 서비스 클래스입니다.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 모든 사용자 정보 리턴
     * @return 모든 사용자 정보
     */
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(UserMapper :: toDTO).collect(Collectors.toList());
    }

    /**
     * Username(id)를 이용해 사용자 검색
     * @param username 유저 아이디
     * @return 유저 DTO를 Optional로 래핑해 리턴. 유저 미존재 시 empty Optional을 리턴함.
     */
    public Optional<UserDTO> findByUsername(String username) {
        Optional<UserEntity> userEntity = userRepository.findById(username);
        Optional<UserDTO> userDTO = Optional.empty();
        log.info("Find By Username for user: {}", username);
        if (userEntity.isPresent()) {
            userDTO = Optional.ofNullable(UserMapper.toDTO(userEntity.get()));
        }
        return userDTO;
    }

    /**
     * 유저 생성 메서드
     * @param userDTO 사용자 정보
     * @return 저장된 사용자 정보를 DTO로 리턴
     */
    public UserDTO createUser(UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        UserEntity result = userRepository.save(UserMapper.toEntity(userDTO));
        return UserMapper.toDTO(result);
    }

    /**
     * 유저 정보 수정. 수정된 모든 데이터를 DB에 적용
     * 삭제 후 저장 과정을 거친다.
     * @param id 유저 아이디
     * @param userDetails 유저 상세정보 DTO
     * @return 수정된 유저 정보
     */
    public UserDTO updateUser(String id, UserDTO userDetails) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        userRepository.deleteById(id);
        UserDTO result = null;
        if (userEntity.isPresent()) {
            UserEntity updated = UserEntity.builder()
                    .name(userDetails.getName())
                    .email(userDetails.getEmail())
                    .password(userDetails.getPassword())
                    .username(userDetails.getUsername())
                    .build();
            result = UserMapper.toDTO(userRepository.save(updated));
        }
        return result;
    }

    /**
     * 유저 삭제. 데이터 반환하지 않는다.
     * @param username 유저 아이디
     */
    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }
}
