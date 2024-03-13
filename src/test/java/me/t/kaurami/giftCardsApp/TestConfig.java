package me.t.kaurami.giftCardsApp;

import jakarta.annotation.PostConstruct;
import me.t.kaurami.giftCardsApp.entities.Category;
import me.t.kaurami.giftCardsApp.entities.User;
import me.t.kaurami.giftCardsApp.repositories.CategoryRepository;
import me.t.kaurami.giftCardsApp.services.notificationservice.NotificationService;
import me.t.kaurami.giftCardsApp.services.notificationservice.events.AbstractEvent;
import org.mockito.Mockito;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;

@TestConfiguration
@Profile("test")
public class TestConfig {

    @Bean
    CommandLineRunner runner(CategoryRepository categoryRepository) {
        return args -> {
            categoryRepository.save(Category.withDescription("NY"));
            categoryRepository.save(Category.withDescription("ONY"));
            categoryRepository.save(Category.withDescription("B"));
        };
    }

    @MockBean
    NotificationService notificationService;

    @PostConstruct
    void setUpMockBean() {
        User user = Mockito.mock(User.class);
        Mockito.when(user.getUsername()).thenReturn("test user");
        AbstractEvent abstractEvent = Mockito.mock(AbstractEvent.class);
        Mockito.when(abstractEvent.getEventFor()).thenReturn(user);
        Mockito.when(abstractEvent.getEventFrom()).thenReturn(user);

        Mockito.when(notificationService.getNotDelivaredEventForUser(any()))
                .thenReturn(Arrays.asList(abstractEvent));
    }

}
