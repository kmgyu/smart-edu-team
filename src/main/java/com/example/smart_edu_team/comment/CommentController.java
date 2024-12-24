package com.example.smart_edu_team.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Objects;
import java.util.Optional;

/**
 * 댓글에 대한 컨트롤러입니다.
 * 생성, 수정, 삭제 모두 Post 메소드임에 주의하십시오.
 */
@RequestMapping("/comments")
@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    /**
     * 댓글을 생성합니다.
     * 생성 후 게시글로 리다이렉트 시킵니다.
     * @param postId 게시글 아이디입니다.
     * @param commentDTO 댓글 데이터입니다.
     * @param principal 작성자 이름을 받아올 때 필요합니다.
     * @return 게시글 아이디를 기반으로 게시글 페이지로 리다이렉트시킵니다.
     */
    @PostMapping("/{postId}/create")
    public String createComment(@PathVariable Long postId, @ModelAttribute CommentDTO commentDTO, Principal principal) {
        commentService.createComment(commentDTO, postId, principal.getName());
        return "redirect:/posts/detail/" + postId.toString();
    }

    /**
     * 댓글을 수정 후 리다이렉트 합니다.
     * 댓글 미존재시, not_found, 댓글 수정 권한이 없을 시, bad_request 템플릿을 반환합니다.
     * @param postId 게시글 아이디입니다. 리다이렉트 시 요구됩니다.
     * @param commentDTO 수정할 댓글 데이터입니다.
     * @param principal 작성자 이름을 받아올 때 필요합니다.
     * @return 게시글 아이디를 기반으로 게시글 페이지로 리다이렉트시킵니다.
     */
    @PostMapping("/{postId}/edit")
    public String updateComment(@PathVariable Long postId, @ModelAttribute CommentDTO commentDTO, Principal principal) {
        Optional<CommentDTO> origin = commentService.findById(commentDTO.getId());
        if (origin.isEmpty()) {
            return "post/not_found";
        }
        if (!Objects.equals(origin.get().getAuthor(), principal.getName())
                && !Objects.equals(principal.getName(), "admin")) {
            return "post/bad_request";
        }
        commentService.updateComment(commentDTO);
        return "redirect:/posts/detail/" + postId.toString();
    }

    /**
     * 댓글 삭제 메소드입니다. 삭제 후 리다이렉트 합니다. Post 요청인 것에 주의
     * 댓글 미존재시, not_found, 댓글 수정 권한이 없을 시, bad_request 템플릿을 반환합니다.
     * @param postId 게시글 아이디입니다.
     * @param id 삭제할 댓글 아이디입니다.
     * @param principal 작성자 이름을 받아올 때 필요합니다.
     * @return 게시글 아이디를 기반으로 게시글 페이지로 리다이렉트시킵니다.
     */
    @PostMapping("/{postId}/delete/{id}")
    public String deleteComment(@PathVariable Long postId, @PathVariable Long id, Principal principal) {
        Optional<CommentDTO> origin = commentService.findById(id);
        if (origin.isEmpty()) {
            return "post/not_found";
        }
        if (!Objects.equals(origin.get().getAuthor(), principal.getName())
                && !Objects.equals(principal.getName(), "admin")) {
            return "post/bad_request";
        }
        commentService.deleteComment(id);
        return "redirect:/posts/detail/" + postId.toString();
    }

    /**
     * 대댓글 생성 메서드입니다.
     * @param postId
     * @param parentCommentId
     * @param commentDTO
     * @param principal
     * @return
     */
    @PostMapping("/{postId}/reply/{parentCommentId}")
    public String createReply(@PathVariable Long postId,
                              @PathVariable Long parentCommentId,
                              @ModelAttribute CommentDTO commentDTO,
                              Principal principal) {
        commentService.createReply(commentDTO, postId, parentCommentId, principal.getName());
        return "redirect:/posts/detail/" + postId.toString();
    }
}
