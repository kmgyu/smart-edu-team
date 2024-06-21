package com.example.smart_edu_team.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findById(username);
    }

    public UserEntity createUser(UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userRepository.save(userEntity);
    }

    public UserEntity updateUser(String id, UserEntity userEntityDetails) {
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        if (userEntity != null) {
            userEntity.setName(userEntityDetails.getName());
            userEntity.setEmail(userEntityDetails.getEmail());
            userEntity.setPassword(userEntityDetails.getPassword());
            userEntity.setUsername(userEntityDetails.getUsername());
            return userRepository.save(userEntity);
        }
        return null;
    }

    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }
}
