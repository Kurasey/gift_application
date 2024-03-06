package me.t.kaurami.giftCardsApp.repositories;

import me.t.kaurami.giftCardsApp.entities.User;
import me.t.kaurami.giftCardsApp.entities.SubscriptionDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubscribeRepository extends JpaRepository<SubscriptionDetails, Long> {

    public Optional<SubscriptionDetails> findBySubscriberAndReceiver(User subscriber, User receiver);

    public void deleteBySubscriberAndReceiver(User subscriber, User receiver);

    public List<SubscriptionDetails> findByReceiver (User receiver);
    public List<SubscriptionDetails> findBySubscriber (User subscriber);

}
