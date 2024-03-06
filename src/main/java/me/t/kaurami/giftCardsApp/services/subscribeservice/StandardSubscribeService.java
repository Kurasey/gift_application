package me.t.kaurami.giftCardsApp.services.subscribeservice;

import me.t.kaurami.giftCardsApp.entities.User;
import me.t.kaurami.giftCardsApp.entities.SubscriptionDetails;
import me.t.kaurami.giftCardsApp.repositories.CategoryRepository;
import me.t.kaurami.giftCardsApp.repositories.SubscribeRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class StandardSubscribeService implements SubscribeService {

    private SubscribeRepository subscribeRepository;
    private CategoryRepository categoryRepository;

    public StandardSubscribeService(SubscribeRepository subscribeRepository, CategoryRepository categoryRepository) {
        this.subscribeRepository = subscribeRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    @PreAuthorize("#subscriber.username.equals(authentication.principal.username)")
    public SubscriptionDetails initiateRequest(User subscriber, User receiver) {
        SubscriptionDetails subscribe = subscribeRepository.save(new SubscriptionDetails(
                subscriber,
                receiver,
                categoryRepository.findByUser(receiver).stream().filter(c->c.isAvailableOnDefault()).collect(Collectors.toList())));
        return subscribe;
    }

    @Override
    @PostAuthorize("returnObject.receiver.username.equals(authentication.principal.username) || " +
            "returnObject.subscriber.username.equals(authentication.principal.username)")
    public SubscriptionDetails getSubscribeInfo(User subscriber, User receiver){
        return subscribeRepository.findBySubscriberAndReceiver(subscriber, receiver).get();
    }
    @Override
    @PostAuthorize("returnObject.receiver.username.equals(authentication.principal.username)")
    public SubscriptionDetails getSubscribeInfo(Long id){
        return subscribeRepository.findById(id).get();
    }

    @Override
    @PreAuthorize("#details.receiver.username.equals(authentication.principal.username)")
    public SubscriptionDetails customizeSubscribe(SubscriptionDetails details) throws IllegalAccessException{
        if (subscribeRepository.findById(details.getId()).get().equals(details)){
            return subscribeRepository.save(details);
        }
        throw new NoSuchElementException("Подписка не найдена");
    }

    @Override
    public void cancelSubscription(User subscriber, User receiver) {
        subscribeRepository.delete(subscribeRepository.findBySubscriberAndReceiver(subscriber,receiver).get());
    }

    @Override
    public List<User> getSubscribers(User receiver) {
        return subscribeRepository.findByReceiver(receiver)
                .stream().map(subscribe -> subscribe.getSubscriber()).collect(Collectors.toList());
    }

    @Override
    public List<User> getSubscriptions(User subscriber) {
        return subscribeRepository.findBySubscriber(subscriber)
                .stream().map(subscribe -> subscribe.getReceiver()).collect(Collectors.toList());
    }

}
