package me.t.kaurami.giftCardsApp.controllers;

import me.t.kaurami.giftCardsApp.entities.User;
import me.t.kaurami.giftCardsApp.repositories.EventRepository;
import me.t.kaurami.giftCardsApp.services.notificationservice.NotificationService;
import me.t.kaurami.giftCardsApp.services.notificationservice.events.AbstractEvent;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@ControllerAdvice
@SessionAttributes("events")
public class NotifyAdvice {

    private NotificationService notificationService;

    public NotifyAdvice(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @ModelAttribute("events")
    public List<AbstractEvent> events(@AuthenticationPrincipal User user) {
        return notificationService.getNotDelivaredEventForUser(user);
    }
}
