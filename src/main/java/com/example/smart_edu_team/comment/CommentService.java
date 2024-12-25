package com.example.smart_edu_team.comment;

import com.example.smart_edu_team.notification.NotificationService;
import com.example.smart_edu_team.post.PostDTO;
import com.example.smart_edu_team.post.PostEntity;
import com.example.smart_edu_team.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 댓글에 대한 CRUD를 제공하는 클래스입니다.
 * 댓글또한 Unique한 ID를 가지고 있기 때문에 Create와 Read 에서만 Post의 ID가 요구됩니다.
 */
@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final NotificationService notificationService;

    /**
     * 게시글 ID를 기준으로 모든 댓글을 가져옵니다. 댓글 하나만 가져오지 않는 것에 주의하십시오.
     * @param postId 게시글 아이디입니다.
     * @return CommentDTO를 List 형태로 리턴합니다. 만약 게시글이 없을 시, 빈 ArrayList를 리턴합니다.
     */
    public List<CommentDTO> getAllComments(long postId) {
        Optional<PostEntity> postEntity = postRepository.findById(postId);
        if (postEntity.isEmpty()) {
            return new ArrayList<>();
        }

        List<CommentEntity> allComments = commentRepository.findAllByPostId(postId);

        // 최상위 댓글 필터링
        List<CommentDTO> parentComments = allComments.stream()
                .filter(comment -> comment.getParentCommentId() == null)
                .map(CommentMapper::toDTO)
                .collect(Collectors.toList());

        // 대댓글 추가
        parentComments.forEach(parent -> {
            List<CommentDTO> replies = allComments.stream()
                    .filter(comment -> parent.getId().equals(comment.getParentCommentId()))
                    .map(CommentMapper::toDTO)
                    .collect(Collectors.toList());
            parent.setReplies(replies);
        });

        return parentComments;
    }

    /**
     * 댓글 하나를 반환합니다.
     * @param id 댓글 아이디입니다.
     * @return 댓글을 Optional로 래핑해 반환합니다. 댓글 미존재시 empty합니다.
     */
    public Optional<CommentDTO> findById(long id) {
        Optional<CommentEntity> commentEntity = commentRepository.findById(id);
        Optional<CommentDTO> commentDTO = Optional.empty();
        if (commentEntity.isPresent()) {
            commentDTO = Optional.of(CommentMapper.toDTO(commentEntity.get()));
        }
        return commentDTO;
    }

    /**
     * commentDTO 및 postId를 참고하여 댓글을 생성하고 리턴합니다.
     * commentDTO는 post를 참고하지 않음에 주의하십시오.
     * @param commentDTO 댓글 DTO. 작성 및 수정시간을 서버시간으로 다시 초기화합니다.
     * @param postId 참고할 게시글 아이디입니다. 미 존재시 commentDTO는 empty한 상태로 리턴합니다.
     * @param author 작성자 이름입니다.
     * @return commentDTO를 리턴합니다. 서버에서 초기화한 수정 시간 및 작성시간이 포함되어있습니다. 만약 오류 발생 시 empty한 상태로 리턴합니다.
     */
    public Optional<CommentDTO> createComment(CommentDTO commentDTO, long postId, String author) {
        Optional<PostEntity> postEntity = postRepository.findById(postId);
        Optional<CommentEntity> commentEntity = Optional.empty();
        if (postEntity.isPresent()) {
            CommentEntity comment = CommentEntity.builder()
                    .content(commentDTO.getContent())
                    .author(author)
                    .edited_time(LocalDateTime.now())
                    .posted_time(LocalDateTime.now())
                    .post(postEntity.get())
                    .build();
            commentEntity = Optional.of(comment);
            commentRepository.save(comment);
            notificationService.createNotification(
                    postEntity.get().getAuthor(),
                    String.format("%s님이 게시글 '%s'에 댓글을 작성했습니다.", author, postEntity.get().getTitle())
            );
            return Optional.of(commentDTO);
        }
        return Optional.empty();
    }

    /**
     * commentDTO를 참고하여 댓글을 수정하고 리턴합니다.
     * commentDTO는 post를 참고하지 않음에 주의하십시오.
     * @param commentDTO 댓글 DTO. 작성 및 수정시간을 서버시간으로 다시 초기화합니다.
     * @return commentDTO를 리턴합니다. 서버에서 초기화한 수정 시간이 포함되어있습니다. comment가 존재하는지 검사하므로 불만족 시 empty한 상태로 리턴합니다.
     */
    public Optional<CommentDTO> updateComment(CommentDTO commentDTO) {
        Optional<CommentEntity> commentEntity = commentRepository.findById(commentDTO.getId());
        if (commentEntity.isPresent()) {
            commentRepository.delete(commentEntity.get());
            CommentEntity updatedComment = commentEntity.get();
            updatedComment.setEdited_time(LocalDateTime.now());
            updatedComment.setContent(commentDTO.getContent());

            commentRepository.save(updatedComment);
            return Optional.of(CommentMapper.toDTO(updatedComment));
        }
        return Optional.empty();
    }

    /**
     * 댓글 삭제 메소드입니다.
     * @param id 댓글 아이디입니다.
     * @return 댓글 확인 및 삭제 시 true를 리턴합니다. 미확인 시 false를 리턴합니다.
     */
    public boolean deleteComment(Long id) {
        Optional<CommentEntity> commentEntity = commentRepository.findById(id);
        if (commentEntity.isPresent()) {
            commentRepository.delete(commentEntity.get());
            return true;
        }
        return false;
    }

    /**
     * 대댓글 생성 메서드입니다. 작성글 및 부모댓글 존재 확인 후 생성합니다.
     * @param commentDTO
     * @param postId
     * @param parentCommentId
     * @param author
     * @return
     */
    public Optional<CommentDTO> createReply(CommentDTO commentDTO, Long postId, Long parentCommentId, String author) {
        // 게시글 확인
        Optional<PostEntity> postEntity = postRepository.findById(postId);
        if (postEntity.isEmpty()) {
            return Optional.empty();
        }

        // 부모 댓글 확인
        Optional<CommentEntity> parentComment = commentRepository.findById(parentCommentId);
        if (parentComment.isEmpty()) {
            return Optional.empty();
        }

        // 대댓글 생성
        CommentEntity reply = CommentEntity.builder()
                .content(commentDTO.getContent())
                .author(author)
                .edited_time(LocalDateTime.now())
                .posted_time(LocalDateTime.now())
                .post(postEntity.get())
                .parentCommentId(parentCommentId) // 부모 댓글 ID 설정
                .build();

        commentRepository.save(reply);

        return Optional.of(CommentMapper.toDTO(reply));
    }
}
