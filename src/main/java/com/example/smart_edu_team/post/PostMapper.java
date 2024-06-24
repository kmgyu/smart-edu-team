package com.example.smart_edu_team.post;

import com.example.smart_edu_team.comment.CommentMapper;

import java.util.stream.Collectors;

public class PostMapper {
    /**
     * postEntity를 postDTO로 변환하여 반환합니다.
     * @param postEntity
     * @return postDTO를 반환합니다.
     */
    public static PostDTO toDTO(PostEntity postEntity) {
        return PostDTO.builder()
                .id(postEntity.getId())
                .title(postEntity.getTitle())
                .author(postEntity.getAuthor())
                .content(postEntity.getContent())
                .posted_time(postEntity.getPosted_time())
                .edited_time(postEntity.getEdited_time())
                .build();
    }

    /**
     * postDTO를 postEntity로 변환하여 반환합니다.
     * @param postDTO
     * @return postEntity를 반환합니다.
     */
    public static PostEntity toEntity(PostDTO postDTO) {
        return PostEntity.builder()
                .id(postDTO.getId())
                .title(postDTO.getTitle())
                .author(postDTO.getAuthor())
                .content(postDTO.getContent())
                .posted_time(postDTO.getPosted_time())
                .edited_time(postDTO.getEdited_time())
                .build();
    }
}
