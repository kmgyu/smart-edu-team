package com.example.smart_edu_team.comment;

import com.example.smart_edu_team.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Comment에 대한 DB CRUD repository입니다.
 * 런타임에 구현됩니다.
 */
@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    /**
     * 게시글 아이디를 기준으로 검색합니다. 하나 이상의 댓글을 반환합니다.
     * @param id 게시글 아이디
     * @return 댓글 엔티티를 List 형태로 리턴합니다.
     */
    public List<CommentEntity> findAllByPostId(Long id);
}
