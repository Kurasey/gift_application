package me.t.kaurami.giftCardsApp.services.proxies;

import me.t.kaurami.giftCardsApp.services.notificationservice.events.SubscriptionInitialized;
import me.t.kaurami.giftCardsApp.entities.SubscriptionDetails;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class NotifyingAspect{

    @Autowired
    ApplicationEventPublisher publisher;


    @Pointcut("execution(public * me.t.kaurami.giftCardsApp.services.subscribeservice.SubscribeService.initiateRequest(..))")
    public void initialSubscribe(){}

    @AfterReturning(pointcut = "initialSubscribe()", returning = "retVal")
    private void createNotification(JoinPoint joinPoint, SubscriptionDetails retVal){
        SubscriptionInitialized event = new SubscriptionInitialized(
                joinPoint.getThis(),
                retVal);
        publisher.publishEvent(event);
    }

}

