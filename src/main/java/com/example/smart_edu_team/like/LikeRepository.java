package com.example.smart_edu_team.like;

import com.example.smart_edu_team.comment.CommentEntity;
import com.example.smart_edu_team.post.PostEntity;
import com.example.smart_edu_team.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Long> {
    Optional<LikeEntity> findByUserAndPost(UserEntity user, PostEntity post);
    Optional<LikeEntity> findByUserAndComment(UserEntity user, CommentEntity comment);
}
