package me.t.kaurami.giftCardsApp.controllers.notification;

import me.t.kaurami.giftCardsApp.repositories.EventRepository;
import me.t.kaurami.giftCardsApp.services.notificationservice.NotificationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/notification")
public class NotificationController {

    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public String getNotificationListForm() {
        return "notificationList";
    }

    @PostMapping(params = "setDeliveredId")
    public String setEventDelivered(@RequestParam("setDeliveredId")Long setDeliveredId){
        notificationService.setEventDelivered(setDeliveredId);
        return "redirect:/notification";
    }
}
