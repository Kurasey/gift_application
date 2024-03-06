package me.t.kaurami.giftCardsApp.configs;

import me.t.kaurami.giftCardsApp.controllers.AvailableInHeader;
import me.t.kaurami.giftCardsApp.controllers.annotations.ViewMenuItem;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring6.ISpringTemplateEngine;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.AbstractTemplateResolver;
import org.thymeleaf.templateresolver.DefaultTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.*;

@Configuration
public class WebConfig implements WebMvcConfigurer{

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/styles/**").addResourceLocations("classpath:/static/styles/");
        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
    }

    @Bean
    List<AvailableInHeader> availableInHeaderList(ConfigurableListableBeanFactory beanFactory, List<AvailableInHeader> availableInHeaderList, MessageSource messageSource) {
        beanFactory.getBeansWithAnnotation(ViewMenuItem.class).values().stream().forEach(v -> {
            ViewMenuItem menuItem = v.getClass().getAnnotation(ViewMenuItem.class);
            availableInHeaderList.add(new AvailableInHeader() {
                @Override
                public String getName() {
                    return messageSource.getMessage(menuItem.title(), null, LocaleContextHolder.getLocale());
                }
                @Override
                public String getReference() {
                    return menuItem.controllerClass().getAnnotation(RequestMapping.class).value()[0];
                }

                @Override
                public int getOrder() {
                    return menuItem.order();
                }
            });

        });
        availableInHeaderList.sort((a1,a2) -> a1.getOrder() - a2.getOrder());
        return availableInHeaderList;
    }

}
