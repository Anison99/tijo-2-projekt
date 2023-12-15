package com.library.tijoLibrary.services;

import com.library.tijoLibrary.models.Notification;
import com.library.tijoLibrary.models.User;
import com.library.tijoLibrary.repositories.NotificationRepository;
import com.library.tijoLibrary.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    public void notifyUser(Long userId, String message) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            Notification notification = new Notification();
            notification.setUser(user.get());
            notification.setMessage(message);
            notification.setTimestamp(LocalDateTime.now());
            notificationRepository.save(notification);
        }
    }
}

