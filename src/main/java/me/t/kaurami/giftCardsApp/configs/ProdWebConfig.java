package me.t.kaurami.giftCardsApp.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Profile("prod")
public class ProdWebConfig  implements WebMvcConfigurer {

    @Autowired
    void configureDispatcherServlet(DispatcherServlet dispatcherServlet) {
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
    }
}

