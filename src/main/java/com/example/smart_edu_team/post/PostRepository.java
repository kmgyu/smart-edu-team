package com.example.smart_edu_team.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Post에 대한 DB CRUD repository입니다.
 * 런타임에 구현됩니다.
 */
@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
