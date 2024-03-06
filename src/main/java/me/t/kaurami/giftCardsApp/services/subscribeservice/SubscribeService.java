package me.t.kaurami.giftCardsApp.services.subscribeservice;

import me.t.kaurami.giftCardsApp.entities.User;
import me.t.kaurami.giftCardsApp.entities.SubscriptionDetails;

import java.util.List;

public interface SubscribeService {

    SubscriptionDetails initiateRequest(User subscriber, User receiver);

    SubscriptionDetails getSubscribeInfo(User subscriber, User receiver);

    SubscriptionDetails getSubscribeInfo(Long id);

    SubscriptionDetails customizeSubscribe(SubscriptionDetails details) throws IllegalAccessException;

    void cancelSubscription(User subscriber, User receiver);

    List<User> getSubscribers(User receiver);

    List<User> getSubscriptions(User subscriber);
}
