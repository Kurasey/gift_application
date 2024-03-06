package me.t.kaurami.giftCardsApp.services.notificationservice.events;

import org.springframework.context.ApplicationEvent;


public abstract class AbstractGiftEvent extends ApplicationEvent{

    protected long id;

    public AbstractGiftEvent(Object source) {
        super(source);
    }
}
