package com.example.smart_edu_team.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    /**
     * 읽지 않은 알림 목록을 가져옵니다.
     * 현재 로그인한 사용자의 알림 중 읽지 않은 알림만 반환합니다.
     *
     * @param principal 현재 로그인한 사용자의 정보를 제공하는 Principal 객체
     * @return 읽지 않은 알림 목록
     */
    @GetMapping("/unread")
    public List<NotificationEntity> getUnreadNotifications(Principal principal) {
        return notificationService.getUnreadNotifications(principal.getName());
    }

    /**
     * 특정 알림을 읽음 상태로 변경합니다.
     *
     * @param id 읽음으로 표시할 알림의 ID
     */
    @PostMapping("/read/{id}")
    public void markAsRead(@PathVariable Long id) {
        notificationService.markAsRead(id);
    }
}