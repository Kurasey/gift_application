package me.t.kaurami.giftCardsApp.services.notificationservice;

import me.t.kaurami.giftCardsApp.entities.User;
import me.t.kaurami.giftCardsApp.services.notificationservice.events.AbstractEvent;

import java.util.List;

public interface NotificationService {

    public void setEventDelivered(Long eventId);

    List<AbstractEvent> getNotDelivaredEventForUser(User user);
}
