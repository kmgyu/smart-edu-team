package com.example.smart_edu_team.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * User에 대한 DB CRUD repository입니다.
 * 인터페이스로 추상화되어 런타임에 구현됩니다.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
}