package com.example.smart_edu_team;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 메인 컨트롤러입니다.
 * 루트 디렉토리로 향하는 요청이 올시, 리다이렉트 시킵니다.
 */
@Controller
public class MainController {

    @GetMapping("/")
    public String root() {
        return "redirect:/posts/index";
    }
}
