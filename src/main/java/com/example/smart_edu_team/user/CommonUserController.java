package com.example.smart_edu_team.user;

import com.example.smart_edu_team.user.infrastructure.UserController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Objects;
import java.util.Optional;

/**
 * 일반 사용자 컨트롤러입니다.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class CommonUserController implements UserController {

    private final UserService userService;


    @Override
    public String getUserByUsername(@PathVariable String username, Model model, Principal principal) {
        Optional<UserDTO> user = userService.findByUsername(username);
        if (user.isPresent() &&
                Objects.equals(principal.getName(), username)) {
            model.addAttribute("user", user.get());
            return "user/details";
        }
        return "user/not_found";
    }

    @Override
    public String showSignupForm(Model model) {
        model.addAttribute("user", new UserDTO());
        return "user/signup";
    }

    @Override
    public String signup(@ModelAttribute UserDTO UserDTO, BindingResult bindingResult) {
        if (userService.findByUsername(UserDTO.getUsername()).isPresent()) {
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "user/signup";
        }
        userService.createUser(UserDTO);
        return "redirect:/posts/index";
    }

    @Override
    public String showLoginForm(Model model) {
        model.addAttribute("user", new UserDTO());
        return "user/login";
    }

    @Override
    public String showEditForm(@PathVariable String username, Model model, Principal principal) {
        Optional<UserDTO> user = userService.findByUsername(username);
        if (user.isPresent() &&
                Objects.equals(principal.getName(), username)) {
            model.addAttribute("user", user.get());
            return "user/edit";
        }
        return "user/not_found";
    }

    @Override
    public String updateUser(@PathVariable String username, @ModelAttribute UserDTO userDetails, Principal principal) {
        if ( Objects.equals(principal.getName(), username)) {
            userService.updateUser(username, userDetails);
            return "redirect:/post/index";
        } else {
            return "user/not_found";
        }
    }

    @Override
    public String deleteUser(@PathVariable String username, Principal principal) {
        if ( Objects.equals(principal.getName(), username)) {
            userService.deleteUser(username);
            return "redirect:/post/index";
        } else {
            return "user/not_found";
        }
    }
}
