package me.t.kaurami.giftCardsApp;

import me.t.kaurami.giftCardsApp.entities.Category;
import me.t.kaurami.giftCardsApp.repositories.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
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
}
