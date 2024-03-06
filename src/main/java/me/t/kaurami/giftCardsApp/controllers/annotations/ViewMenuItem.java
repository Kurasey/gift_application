package me.t.kaurami.giftCardsApp.controllers.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewMenuItem {
    Class<?> controllerClass ();
    String title();

    int order();

}
