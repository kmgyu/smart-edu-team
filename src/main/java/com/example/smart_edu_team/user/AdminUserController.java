package com.example.smart_edu_team.user;

import com.example.smart_edu_team.user.infrastructure.UserController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admins")
public class AdminUserController implements UserController {
    private final UserService userService;

    /**
     * admin 전용 유저 관리 페이지
     * @param model
     * @return
     */
    @GetMapping("/")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user/index";
    }

    @Override
    @GetMapping("/{username}")
    public String getUserByUsername(@PathVariable String username, Model model) {
        Optional<User> user = userService.findByUsername(username);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "user/details";
        }
        return "user/not_found";
    }

    @Override
    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "user/signup";
    }

    @Override
    @PostMapping("/signup")
    public String signup(@ModelAttribute User user, BindingResult bindingResult) {
        if (userService.findByUsername(user.getUsername()).isPresent()) {
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "user/signup";
        }
        userService.createUser(user);
        return "redirect:/users";
    }

    @Override
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "user/login";
    }

    @Override
    @GetMapping("/edit/{username}")
    public String showEditForm(@PathVariable String username, Model model) {
        Optional<User> user = userService.findByUsername(username);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "user/edit";
        }
        return "user/not_found";
    }

    @Override
    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable String id, @ModelAttribute User userDetails) {
        userService.updateUser(id, userDetails);
        return "redirect:/users";
    }

    @Override
    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
