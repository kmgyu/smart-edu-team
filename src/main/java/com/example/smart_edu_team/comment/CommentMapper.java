package com.example.smart_edu_team.comment;

import com.example.smart_edu_team.post.PostEntity;

/**
 * Comment의 매퍼 클래스입니다.
 */
public class CommentMapper {
    /**
     * CommentEntity를 CommentDTO로 변환하여 반환합니다.
     * @param commentEntity 댓글 엔티티
     * @return CommentDTO를 반환합니다.
     */
    public static CommentDTO toDTO(CommentEntity commentEntity) {
        return CommentDTO.builder()
                .id(commentEntity.getId())
                .author(commentEntity.getAuthor())
                .content(commentEntity.getContent())
                .edited_time(commentEntity.getEdited_time())
                .posted_time(commentEntity.getPosted_time())
                .build();
    }

    /**
     * CommentDTO를 CommentEntity로 변환합니다. PostEntity가 추가로 요구됩니다.
     * @param commentDTO 댓글 DTO
     * @param post 게시글 엔티티
     * @return CommentEntity를 반환합니다.
     */
    public static CommentEntity toEntity(CommentDTO commentDTO, PostEntity post) {
        return CommentEntity.builder()
                .id(commentDTO.getId())
                .author(commentDTO.getAuthor())
                .content(commentDTO.getContent())
                .edited_time(commentDTO.getEdited_time())
                .posted_time(commentDTO.getPosted_time())
                .postEntity(post)
                .build();
    }
}
