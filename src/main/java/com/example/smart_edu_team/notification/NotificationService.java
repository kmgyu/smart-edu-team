package com.example.smart_edu_team.notification;

import com.example.smart_edu_team.user.UserEntity;
import com.example.smart_edu_team.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    /**
     * 알림을 생성합니다.
     * 특정 사용자에게 메시지를 포함한 알림을 저장합니다.
     *
     * @param username 알림을 받을 사용자의 ID
     * @param message 알림 메시지
     * @throws IllegalArgumentException 사용자 ID가 유효하지 않을 경우 예외를 발생시킵니다.
     */
    public void createNotification(String username, String message) {
        Optional<UserEntity> user = userRepository.findById(username);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        NotificationEntity notification = NotificationEntity.builder()
                .user(user.get())
                .message(message)
                .build();
        notificationRepository.save(notification);
    }

    /**
     * 읽지 않은 알림 목록을 반환합니다.
     * 특정 사용자에게 해당하는 읽지 않은 알림만 반환합니다.
     *
     * @param username 알림을 조회할 사용자의 ID
     * @return 읽지 않은 알림 목록
     * @throws IllegalArgumentException 사용자 ID가 유효하지 않을 경우 예외를 발생시킵니다.
     */
    public List<NotificationEntity> getUnreadNotifications(String username) {
        Optional<UserEntity> user = userRepository.findById(username);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        return notificationRepository.findByUserAndIsReadFalse(user.get());
    }

    /**
     * 특정 알림을 읽음 상태로 변경합니다.
     *
     * @param notificationId 읽음으로 표시할 알림의 ID
     */
    public void markAsRead(Long notificationId) {
        Optional<NotificationEntity> notification = notificationRepository.findById(notificationId);
        if (notification.isPresent()) {
            NotificationEntity notif = notification.get();
            notif.setRead(true);
            notificationRepository.save(notif);
        }
    }
}