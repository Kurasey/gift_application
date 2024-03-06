package me.t.kaurami.giftCardsApp.services.proxies;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    Logger logger = LoggerFactory.getLogger("METHOD_LOGGER");

    @Before("me.t.kaurami.giftCardsApp.services.proxies.CommonPointcut.anyLoggable()")
    public void log(JoinPoint joinPoint){
        logger.info(joinPoint.toString());
    }

}
