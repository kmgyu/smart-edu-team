package com.example.smart_edu_team.user;

import com.example.smart_edu_team.user.infrastructure.UserController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

/**
 * 관리자용 컨트롤러입니다.
 * url이 일반 사용자와 달라 필터링하기 수월합니다.
 */
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admins")
public class AdminUserController implements UserController {
    private final UserService userService;

    /**
     * admin 전용 유저 관리 페이지
     * @param model
     * @return 회원 목록을 반환합니다.
     */
    @GetMapping("/")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user/index";
    }


    @Override
    public String showSignupForm(Model model) {
        return "redirect:/users/signup";
    }

    @Override
    public String signup(UserDTO userDTO, BindingResult bindingResult) {
        return "redirect:/users/signup";
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
        return "redirect:/admins/";
    }

    @Override
    public String deleteUser(String username, Principal principal) {
        userService.deleteUser(username);
        return "redirect:/admins/";
    }
}
