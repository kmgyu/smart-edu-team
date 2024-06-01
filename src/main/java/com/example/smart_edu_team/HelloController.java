package com.example.smart_edu_team;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/hello")
@Controller
public class HelloController {

    @GetMapping("/")
    public String hello(Model model) {
        // 모델에 데이터 추가
        model.addAttribute("name", "John Doe");
        return "hello"; // 템플릿의 이름을 반환
    }

    @GetMapping("/display")
    public String displayContent(Model model) {
        model.addAttribute("name", "John Doe");
        model.addAttribute("content", "<p>This is <strong>bold</strong> text</p>");
        return "display";
    }
}