package me.t.kaurami.giftCardsApp.services.proxies;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointcut {

    @Pointcut("@annotation(me.t.kaurami.giftCardsApp.services.proxies.Loggable)")
    public void anyLoggable(){}
}
