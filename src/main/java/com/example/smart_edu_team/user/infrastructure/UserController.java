package com.example.smart_edu_team.user.infrastructure;

import com.example.smart_edu_team.user.User;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public interface UserController {
    /**
     * 회원가입에 대한 Get 요청 메소드
     * @param model
     * @return
     */
    @GetMapping("/signup")
    public String showSignupForm(Model model);

    /**
     * 회원가입에 대한 Post 요청 메소드
     * @param user
     * @param bindingResult
     * @return
     */
    @PostMapping("/signup")
    public String signup(@ModelAttribute User user, BindingResult bindingResult);

    /**
     * 로그인에 대한 get 요청 메소드
     * login의 경우 security가 확인하므로 post가 필요하지 않다.
     * @param model
     * @return
     */
    @GetMapping("/login")
    public String showLoginForm(Model model);

    /**
     * username(아이디)에 대해 검색 시 가져온다.
     * @param username
     * @param model
     * @return
     */
    @GetMapping("/{username}")
    public String getUserByUsername(@PathVariable String username, Model model);

    /**
     * 유저 수정 페이지를 가져오는 get 요청 메소드
     * @param username
     * @param model
     * @return
     */
    @GetMapping("/edit/{username}")
    public String showEditForm(@PathVariable String username, Model model);

    /**
     * 유저 수정 페이지 Post 요청 메소드
     * @param id
     * @param userDetails
     * @return
     */
    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable String id, @ModelAttribute User userDetails);

    /**
     * 유저를 삭제하는 요청에 대한 메소드
     * @param id
     * @return
     */
    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id);
}
