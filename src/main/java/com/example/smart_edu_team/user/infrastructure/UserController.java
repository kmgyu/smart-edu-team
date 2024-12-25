package com.example.smart_edu_team.user.infrastructure;

import com.example.smart_edu_team.user.UserDTO;
import com.example.smart_edu_team.user.UserEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

/**
 * 사용자에 대한 컨트롤러입니다.
 * 권한에 맞춰 url을 매핑시켜줄 수 있습니다.
 */
public interface UserController {
    /**
     * 회원가입에 대한 Get 요청 메소드, 관리자 url에서는 무조건 사용자 url로 리다이렉트합니다.
     * @param model
     * @return 회원가입 템플릿 파일 이름을 리턴합니다.
     */
    @GetMapping("/signup")
    public String showSignupForm(Model model);

    /**
     * 회원가입에 대한 PostEntity 요청 메소드, 관리자 url에서는 무조건 사용자 url로 리다이렉트합니다.
     * @param userDTO post할 유저 정보입니다.
     * @param bindingResult 오류 메시지가 바인딩 됩니다.
     * @return 회원가입 템플릿 파일 이름을 리턴합니다.
     */
    @PostMapping("/signup")
    public String signup(@ModelAttribute UserDTO userDTO, BindingResult bindingResult);

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
     * @param username 유저 아이디
     * @param model
     * @param principal 로그인 정보
     * @return 유저 정보 템플릿을 리턴합니다. 만약 유저가 없을 시, 유저 notfound 템플릿을 리턴합니다. 권한이 없을 경우(또는 아이디가 다를 경우)도 동일합니다.
     */
    @GetMapping("/info/{username}")
    public String getUserByUsername(@PathVariable String username, Model model, Principal principal);

    /**
     * 유저 수정 페이지를 가져오는 get 요청 메소드
     * @param username path Variable(=parameter)로 유저 아이디를 받아옵니다.
     * @param model
     * @param principal 로그인 정보
     * @return 유저 정보 수정 템플릿을 리턴합니다. 만약 유저가 없을 시, 유저 notfound 템플릿을 리턴합니다. 권한이 없을 경우(또는 아이디가 다를 경우)도 동일합니다.
     */
    @GetMapping("/edit/{username}")
    public String showEditForm(@PathVariable String username, Model model, Principal principal);

    /**
     * 유저 수정 페이지 PostEntity 요청 메소드
     * @param username path Variable(=parameter)로 유저 아이디를 받아옵니다.
     * @param userDetails Model attribute로 회원 상세 데이터를 받아옵니다.
     * @param principal 로그인 정보
     * @return 유저 목록으로 리다이렉트 합니다. 일반 유저의 경우 게시글 목록으로 리다이렉트 합니다.
     */
    @PostMapping("/edit/{username}")
    public String updateUser(@PathVariable String username, @ModelAttribute UserDTO userDetails, Principal principal);

    /**
     * 유저를 삭제하는 요청에 대한 메소드
     * @param username path Variable(=parameter)로 유저 아이디를 받아옵니다.
     * @param principal 로그인 정보
     * @return 유저 목록으로 리다이렉트 합니다. 일반 유저의 경우 게시글 목록으로 리다이렉트 합니다.
     */
    @PostMapping("/delete/{username}")
    public String deleteUser(@PathVariable String username, Principal principal);
}
