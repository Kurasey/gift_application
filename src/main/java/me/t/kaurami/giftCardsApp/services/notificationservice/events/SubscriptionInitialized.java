package me.t.kaurami.giftCardsApp.services.notificationservice.events;

import me.t.kaurami.giftCardsApp.entities.SubscriptionDetails;

public class SubscriptionInitialized extends AbstractGiftEvent {

    private SubscriptionDetails details;


    public SubscriptionInitialized(Object source, SubscriptionDetails details) {
        super(source);
        this.details = details;
    }

    public String getMessage(){
        return String.format("Подписался пользователь %s", details.getSubscriber());
    }





}
