package com.example.smart_edu_team.user;

import com.example.smart_edu_team.user.infrastructure.UserController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
    public String showSignupForm(Model model) {
        model.addAttribute("user", new UserEntity());
        return "user/signup";
    }

    @Override
    public String signup(UserDTO userDTO, BindingResult bindingResult) {
        if (userService.findByUsername(userDTO.getUsername()).isPresent()) {
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "user/signup";
        }
        userService.createUser(userDTO);
        return "redirect:/users";
    }

    @Override
    public String showLoginForm(Model model) {
        model.addAttribute("user", new UserEntity());
        return "user/login";
    }

    @Override
    public String getUserByUsername(String username, Model model, Principal principal) {
        Optional<UserDTO> user = userService.findByUsername(username);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "user/details";
        }
        return "user/not_found";
    }

    @Override
    public String showEditForm(String username, Model model, Principal principal) {
        Optional<UserDTO> user = userService.findByUsername(username);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "user/edit";
        }
        return "user/not_found";
    }

    @Override
    public String updateUser(String username, UserDTO userEntityDetails, Principal principal) {
        userService.updateUser(username, userEntityDetails);
        return "redirect:/users";
    }

    @Override
    public String deleteUser(String username, Principal principal) {
        userService.deleteUser(username);
        return "redirect:/users";
    }
}
