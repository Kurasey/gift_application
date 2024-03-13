package me.t.kaurami.giftCardsApp.services.proxies;

import me.t.kaurami.giftCardsApp.repositories.EventRepository;
import me.t.kaurami.giftCardsApp.services.notificationservice.events.SubscriptionInitializedEvent;
import me.t.kaurami.giftCardsApp.entities.SubscriptionDetails;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class NotifyingAspect{

    private ApplicationEventPublisher eventPublisher;

    public NotifyingAspect(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Pointcut("execution(public * me.t.kaurami.giftCardsApp.services.subscribeservice.SubscribeService.initiateRequest(..))")
    public void initialSubscribe(){}

    @AfterReturning(pointcut = "initialSubscribe()", returning = "retVal")
    private void createNotification(JoinPoint joinPoint, SubscriptionDetails retVal){
        SubscriptionInitializedEvent event = new SubscriptionInitializedEvent(
                retVal);
        eventPublisher.publishEvent(event);
    }

}

