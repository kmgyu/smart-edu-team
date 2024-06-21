package com.example.smart_edu_team.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query("select u from User u where u.username = :username")
    Optional<User> findByUsername(String username);
}