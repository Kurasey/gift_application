package me.t.kaurami.giftCardsApp.services.notificationservice.events;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import me.t.kaurami.giftCardsApp.entities.SubscriptionDetails;
import me.t.kaurami.giftCardsApp.entities.User;

@Entity
@DiscriminatorValue("subscribe_initialize")
public class SubscriptionInitializedEvent extends AbstractEvent {


    protected SubscriptionInitializedEvent() {
    }

    public SubscriptionInitializedEvent(SubscriptionDetails details) {
        super(details.getSubscriber(), details.getReceiver());
    }


}
