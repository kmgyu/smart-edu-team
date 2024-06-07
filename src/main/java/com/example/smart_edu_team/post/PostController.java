package com.example.smart_edu_team.post;

import com.example.smart_edu_team.user.User;
import com.example.smart_edu_team.user.UserRepository;
import com.example.smart_edu_team.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public String getPostById(@PathVariable String id, Model model) {
        Post post = postService.getPostById(id);
        if (post != null) {
            model.addAttribute("post", post);
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
        Optional<User> user = userService.findByUsername(principal.getName());
        if (user.isPresent()) {
            postService.createPost(post, user.get().getUsername());
        }
        return "redirect:/posts/index";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Post post = postService.getPostById(id);
        if (post != null) {
            model.addAttribute("post", post);
            return "post/edit";
        }
        return "post/not_found";
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
