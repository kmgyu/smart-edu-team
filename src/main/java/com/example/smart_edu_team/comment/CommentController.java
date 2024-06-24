package com.example.smart_edu_team.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Objects;

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
     * @param postId 게시글 아이디입니다. 리다이렉트 시 요구됩니다.
     * @param commentDTO 수정할 댓글 데이터입니다.
     * @param principal 작성자 이름을 받아올 때 필요합니다.
     * @return 게시글 아이디를 기반으로 게시글 페이지로 리다이렉트시킵니다.
     */
    @PostMapping("/{postId}/edit")
    public String updateComment(@PathVariable Long postId, @ModelAttribute CommentDTO commentDTO, Principal principal) {
        if (!Objects.equals(principal.getName(), principal.getName()) && !Objects.equals(principal.getName(), "admin")) {
            return "post/bad_request";
        }
        commentService.updateComment(commentDTO);
        return "redirect:/posts/detail/" + postId.toString();
    }

    /**
     * 댓글 삭제 메소드입니다. 삭제 후 리다이렉트 합니다. Post 요청인 것에 주의
     * @param postId 게시글 아이디입니다.
     * @param id 삭제할 댓글 아이디입니다.
     * @param principal
     * @return 게시글 아이디를 기반으로 게시글 페이지로 리다이렉트시킵니다.
     */
    @PostMapping("/{postId}/delete/{id}")
    public String deleteComment(@PathVariable Long postId, @PathVariable Long id, Principal principal) {
        if (!Objects.equals(principal.getName(), principal.getName()) && !Objects.equals(principal.getName(), "admin")) {
            return "post/bad_request";
        }
        commentService.deleteComment(id);
        return "redirect:/posts/detail/" + postId.toString();
    }

}
