package me.t.kaurami.giftCardsApp.services.notificationservice;

import me.t.kaurami.giftCardsApp.entities.User;
import me.t.kaurami.giftCardsApp.repositories.EventRepository;
import me.t.kaurami.giftCardsApp.services.notificationservice.events.AbstractEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StandardJpaNotificationService implements NotificationService{

    private EventRepository eventRepository;

    public StandardJpaNotificationService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void setEventDelivered(Long eventId) {
        eventRepository.setDelivered(eventId);
    }

    @Override
    public List<AbstractEvent> getNotDelivaredEventForUser(User user) {
        return eventRepository.findByEventForAndDeliveredIsFalse(user);
    }

    @PreAuthorize("#event.eventFrom.username.equals(authentication.principal.username)")
    @EventListener(AbstractEvent.class)
    public void saveSubscriptionInitializedEvent(AbstractEvent event) {
        eventRepository.save(event);
    }
}
