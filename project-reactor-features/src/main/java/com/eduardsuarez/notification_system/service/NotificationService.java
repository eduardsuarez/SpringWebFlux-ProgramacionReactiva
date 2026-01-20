package com.eduardsuarez.notification_system.service;

import com.eduardsuarez.notification_system.models.NotificationEvent;
import reactor.core.publisher.Mono;

public interface NotificationService {
    Mono<Boolean> sendNotification(NotificationEvent event);
}
