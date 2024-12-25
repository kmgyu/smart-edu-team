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

    public List<NotificationEntity> getUnreadNotifications(String username) {
        Optional<UserEntity> user = userRepository.findById(username);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        return notificationRepository.findByUserAndIsReadFalse(user.get());
    }

    public void markAsRead(Long notificationId) {
        Optional<NotificationEntity> notification = notificationRepository.findById(notificationId);
        if (notification.isPresent()) {
            NotificationEntity notif = notification.get();
            notif.setRead(true);
            notificationRepository.save(notif);
        }
    }
}
