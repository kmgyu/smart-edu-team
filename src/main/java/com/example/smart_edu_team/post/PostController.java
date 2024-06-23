package com.example.smart_edu_team.post;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    /**
     * 게시글 목록을 반환합니다.
     * @param model
     * @return 게시글 목록 페이지
     */
    @GetMapping("/index")
    public String getAllPosts(Model model) {
        model.addAttribute("posts", postService.getAllPosts());
        return "post/index";
    }

    /**
     * 게시글 하나의 상세 내용 페이지를 반환합니다.
     * @param id 게시글 id
     * @param model
     * @return 게시글 상세 내용을 반환하나, id에 해당하는 게시글 미존재 시 not_found 템플릿을 반환합니다.
     */
    @GetMapping("/detail/{id}")
    public String getPostById(@PathVariable Long id, Model model) {
        Optional<PostDTO> post = postService.getPostById(id);
        if (post.isPresent()) {
            model.addAttribute("post", post.get());
            return "post/details";
        }
        return "post/not_found";
    }

    /**
     * 게시글 생성 페이지를 반환합니다.
     * @param model
     * @return 게시글 생성 페이지를 반환합니다.
     */
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("post", new PostEntity());
        return "post/create";
    }

    /**
     * 회원 이름을 기반으로 게시글을 생성하고, 게시글 목록으로 리다이렉트합니다.
     * @param postDTO Model attribute로 post를 받아옵니다.
     * @param principal 로그인 정보입니다.
     * @return 게시글 목록으로 리다이렉트합니다.
     */
    @PostMapping("/create")
    public String createPost(@ModelAttribute PostDTO postDTO, Principal principal) {
        postService.createPost(postDTO, principal.getName());
        return "redirect:/posts/index";
    }

    /**
     * 수정 페이지를 리턴합니다. 권한이 없거나 아이디가 다를 경우 bad_request를 반환합니다.
     * @param id 작성글의 아이디입니다.
     * @param model
     * @param principal 로그인 정보입니다.
     * @return 게시글이 없으면 not_found를 반환합니다. 아이디가 다르거나 접근 권한이 없을경우, bad_request를 반환합니다. 조건 충족시, 수정 페이지를 반환합니다.
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, Principal principal) {
        Optional<PostDTO> post = postService.getPostById(id);
        if (post.isEmpty()) {
            return "post/not_found";
        }
        if(!(post.get().getAuthor().equals(principal.getName()) || Objects.equals(principal.getName(), "admin"))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        model.addAttribute("post", post.get());
        return "post/edit";
    }

    /**
     * 게시글 수정 Post 메서드입니다. id를 기반으로 게시글을 수정하고 게시글 목록으로 리다이렉트 합니다.
     * @param id 게시글 아이디
     * @param postDetails 수정할 게시글 내용
     * @return 게시글 목록 리다이렉트
     */
    @PostMapping("/edit/{id}")
    public String updatePost(@PathVariable Long id, @ModelAttribute PostDTO postDetails) {
        postService.updatePost(id, postDetails);
        return "redirect:/posts/index";
    }

    /**
     * 게시글 삭제 메서드입니다. id 기반으로 게시글 삭제 후 게시글 목록으로 리다이렉트 합니다.
     * @param id 게시글 아이디
     * @return 게시글 목록 리다이렉트
     */
    @PostMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return "redirect:/posts/index";
    }
}
