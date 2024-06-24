package com.example.smart_edu_team.comment;

import com.example.smart_edu_team.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    public List<CommentEntity> findAllByPostEntity(PostEntity postEntity);
}
