package com.idp.projeto.components;

import javax.annotation.PostConstruct;

import com.idp.projeto.models.Notification;
import com.idp.projeto.services.NotificationService;

import org.springframework.stereotype.Component;

@Component
public class AplicationStartup {

  @PostConstruct
  private void init() {
    NotificationService notificationService = new NotificationService();
    notificationService.notify(new Notification("localhost", "8081"));

    return;
  }
}
