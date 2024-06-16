package com.example.smart_edu_team.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {
    /**
     * parameter id binding to query, id.
     * Optional에 wrapping된 Post를 반환합니다. isPresent() 사용을 통해 존재 여부 확인이 용이합니다.
     * @param id
     * @return post
     */
    @Query("select p from Post p where p.id = :id")
    public Optional<Post> findByPostId(@Param("id") String id);
}
