package com.example.smart_edu_team.user;

import com.example.smart_edu_team.config.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * UserDetailsService를 상속받는 커스텀 서비스입니다.
 * 유저 권한을 관리합니다.
 */
@Service
@RequiredArgsConstructor
public class UserSecurityService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * 유저에게 권한을 발급하는 메서드입니다.
     * 관리자 계정은 admin 이라는 유저 아이디를 가진 계정 하나만 존재합니다.
     * @param username
     * @return User 객체를 리턴합니다. 유저 아이디와 비밀번호, 권한이 포함됩니다.
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findById(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User Not Found");
        }
        UserEntity _userEntity = user.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        if ("admin".equals(username)) {
            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
        }

        return new User(_userEntity.getUsername(), _userEntity.getPassword(), authorities);
    }
}
