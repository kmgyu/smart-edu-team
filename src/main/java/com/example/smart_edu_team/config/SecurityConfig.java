package com.example.smart_edu_team.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * 사용자 정의 보안 설정 클래스입니다.
 * 보안 설정 시, 가급적이면 해당 클래스의 코드를 수정해야 합니다.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    /**
     * security filter chain입니다. 코드를 수정해 접근 권한이나 토큰 발급 등의 추가적인 보안설정이 가능합니다.
     * @param http
     * @return 해당 설정에 기반해 빌드 된 http를 리턴합니다.
     * @throws Exception
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
//                권한 필터링
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/admins/**").hasRole("ADMIN")
                        .requestMatchers("/users/login").permitAll()
                        .requestMatchers("/users/signup").permitAll()
                        .requestMatchers("/posts/index").permitAll()
                )
//                로그인 성공 시 루트 디렉토리로 이동
                .formLogin((formLogin) -> formLogin
                        .loginPage("/users/login")
                        .defaultSuccessUrl("/"))
//                헤더 설정
                .headers((headers) -> headers
                        .addHeaderWriter(new XFrameOptionsHeaderWriter(
                                XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
//                로그아웃 성공 시 루트 디렉토리 이동
                .logout((logout) -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true))
        ;
        return http.build();
    }

    /**
     * 비밀번호 인코딩 시 사용하는 bean 객체입니다.
     * @return Bcrypt 인코더 인스턴스를 리턴합니다.
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * authentication configuration을 받아 authentication manager를 리턴합니다.
     * @param authenticationConfiguration
     * @return authentication manager를 리턴합니다.
     * @throws Exception
     */
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}