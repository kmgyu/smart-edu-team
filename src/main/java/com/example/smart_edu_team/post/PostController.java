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
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final UserService userService;

    @GetMapping("/index")
    public String getAllPosts(Model model) {
        model.addAttribute("posts", postService.getAllPosts());
        return "post/index";
    }

    @GetMapping("/detail/{id}")
    public String getPostById(@PathVariable String id, Model model) {
        Optional<Post> post = postService.getPostById(id);
        if (post.isPresent()) {
            model.addAttribute("post", post.get());
            return "post/details";
        }
        return "post/not_found";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("post", new Post());
        return "post/create";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute Post post, Principal principal) {
        Optional<UserDTO> user = userService.findByUsername(principal.getName());
        if (user.isPresent()) {
            postService.createPost(post, user.get().getUsername());
        }
        return "redirect:/posts/index";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model, Principal principal) {
        Optional<Post> post = postService.getPostById(id);
        if (post.isEmpty()) {
            return "post/not_found";
        }
        if(!post.get().getAuthor().equals(principal.getName())) {
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
