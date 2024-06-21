package com.example.smart_edu_team.user.infrastructure;

import com.example.smart_edu_team.user.User;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 사용자에 대한 컨트롤러입니다.
 * 권한에 맞춰 url을 매핑시켜줄 수 있습니다.
 */
public interface UserController {
    /**
     * 회원가입에 대한 Get 요청 메소드
     * @param model
     * @return 회원가입 템플릿 파일 이름을 리턴합니다.
     */
    @GetMapping("/signup")
    public String showSignupForm(Model model);

    /**
     * 회원가입에 대한 Post 요청 메소드
     * @param user
     * @param bindingResult
     * @return 회원가입 템플릿 파일 이름을 리턴합니다.
     */
    @PostMapping("/signup")
    public String signup(@ModelAttribute User user, BindingResult bindingResult);

    /**
     * 로그인에 대한 get 요청 메소드
     * login의 경우 security가 확인하므로 post가 필요하지 않다.
     * @param model
     * @return 로그인 템플릿 파일 이름을 리턴합니다.
     */
    @GetMapping("/login")
    public String showLoginForm(Model model);

    /**
     * username(아이디)에 대해 검색 시 가져온다.
     * @param username
     * @param model
     * @return 유저 정보 템플릿을 리턴합니다. 만약 유저가 없을 시, 유저 notfound 템플릿을 리턴합니다.
     */
    @GetMapping("/{username}")
    public String getUserByUsername(@PathVariable String username, Model model);

    /**
     * 유저 수정 페이지를 가져오는 get 요청 메소드
     * @param username
     * @param model
     * @return 유저 정보 수정 템플릿을 리턴합니다. 만약 유저가 없을 시, 유저 notfound 템플릿을 리턴합니다.
     */
    @GetMapping("/edit/{username}")
    public String showEditForm(@PathVariable String username, Model model);

    /**
     * 유저 수정 페이지 Post 요청 메소드
     * @param username
     * @param userDetails
     * @return
     */
    @PostMapping("/edit/{username}")
    public String updateUser(@PathVariable String username, @ModelAttribute User userDetails);

    /**
     * 유저를 삭제하는 요청에 대한 메소드
     * @param username
     * @return
     */
    @PostMapping("/delete/{username}")
    public String deleteUser(@PathVariable String username);
}
