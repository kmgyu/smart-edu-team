package com.example.smart_edu_team.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    /**
     * 모든 작성글을 반환합니다.
     * @return 모든 작성글을 PostDTO의 List 형태로 반환합니다.
     */
    public List<PostDTO> getAllPosts() {
        return postRepository.findAll().stream().map(PostMapper :: toDTO).collect(Collectors.toList());
    }

    /**
     * Post id로 게시글을 검색해 반환합니다.
     * @param id 게시글 아이디
     * @return Optional로 래핑된 게시글을 반환합니다. 미존재 시 empty 일 수 있습니다.
     */
    public Optional<PostDTO> getPostById(String id) {
        Optional<PostEntity> postEntity = postRepository.findById(id);
        PostDTO result = null;
        if (postEntity.isPresent()) {
            result = PostMapper.toDTO(postEntity.get());
        }
        return Optional.ofNullable(result);
    }

    /**
     * 게시글을 생성하고 생성된 게시글을 반환합니다.
     * 서버시간을 기준으로 생성시간, 수정시간이 초기화됩니다.
     * @param postDTO 생성할 게시글입니다.
     * @param author 게시글 작성자
     * @return 생성된 게시글 데이터입니다.
     */
    public PostDTO createPost(PostDTO postDTO, String author) {
        postDTO.setAuthor(author);
        postDTO.setPosted_time(LocalDateTime.now());
        postDTO.setEdited_time(LocalDateTime.now());
        postRepository.save(PostMapper.toEntity(postDTO));
        return postDTO;
    }

    /**
     * 게시글 수정 메서드입니다. 게시글 아이디 기준 검색 후 내용 수정합니다.
     * @param id 게시글 아이디
     * @param postDetails 수정할 게시글 내용
     * @return 수정된 내용을 반환합니다. 게시글 미존재 시, null을 반환합니다.
     */
    public PostDTO updatePost(String id, PostDTO postDetails) {
        Optional<PostEntity> post = postRepository.findById(id);
        PostEntity postEntity1;
        if (post.isPresent()) {
            postEntity1 = post.get();
            postEntity1.setTitle(postDetails.getTitle());
            postEntity1.setContent(postDetails.getContent());
            postEntity1.setAuthor(postDetails.getAuthor());
            postEntity1.setEdited_time(LocalDateTime.now());
            postRepository.save(postEntity1);
            return PostMapper.toDTO(postEntity1);
        }
        return null;
    }

    /**
     * 게시글 삭제 메서드입니다. 아이디로 검색하여 삭제합니다.
     * @param id 게시글 아이디
     * @return 게시글 존재 및 삭제 성공 시 true, 미존재 시 false 반환
     */
    public boolean deletePost(String id) {
        Optional<PostEntity> post = postRepository.findById(id);
        if (post.isPresent()) {
            postRepository.delete(post.get());
            return true;
        }
        return false;
    }
}