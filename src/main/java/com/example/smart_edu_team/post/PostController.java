package com.example.smart_edu_team.post;

import com.example.smart_edu_team.user.UserDTO;
import com.example.smart_edu_team.user.UserEntity;
import com.example.smart_edu_team.user.UserService;
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
    public String getPostById(@PathVariable String id, Model model) {
        Optional<Post> post = postService.getPostById(id);
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
        model.addAttribute("post", new Post());
        return "post/create";
    }

    /**
     *
     * @param post Model attribute로 post를 받아옵니다.
     * @param principal
     * @return
     */
    @PostMapping("/create")
    public String createPost(@ModelAttribute Post post, Principal principal) {
        postService.createPost(post, principal.getName());
        return "redirect:/posts/index";
    }

    /**
     * 수정 페이지를 리턴합니다. 권한이 없거나 아이디가 다를 경우 bad_request를 반환합니다.
     * @param id 작성글의 아이디입니다.
     * @param model
     * @param principal
     * @return 게시글이 없으면 not_found를 반환합니다. 아이디가 다르거나 접근 권한이 없을경우, bad_request를 반환합니다. 조건 충족시, 수정 페이지를 반환합니다.
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model, Principal principal) {
        Optional<Post> post = postService.getPostById(id);
        if (post.isEmpty()) {
            return "post/not_found";
        }
        if(!(post.get().getAuthor().equals(principal.getName()) || Objects.equals(principal.getName(), "admin"))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        model.addAttribute("post", post.get());
        return "post/edit";
    }

    @PostMapping("/edit/{id}")
    public String updatePost(@PathVariable String id, @ModelAttribute Post postDetails) {
        postService.updatePost(id, postDetails);
        return "redirect:/posts/index";
    }

    @PostMapping("/delete/{id}")
    public String deletePost(@PathVariable String id) {
        postService.deletePost(id);
        return "redirect:/posts/index";
    }
}
