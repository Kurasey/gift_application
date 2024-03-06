package me.t.kaurami.giftCardsApp.services.notificationservice;

import me.t.kaurami.giftCardsApp.entities.User;
import me.t.kaurami.giftCardsApp.services.notificationservice.events.AbstractGiftEvent;

import java.util.List;

public abstract class NotificationService {

    private User source;
    private AbstractGiftEvent event;
    private List<User> recipients;

    public abstract void notifyAboutEvent(String event);

}
