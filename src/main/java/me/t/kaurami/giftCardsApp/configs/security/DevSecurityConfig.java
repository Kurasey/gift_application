package me.t.kaurami.giftCardsApp.configs.security;

import me.t.kaurami.giftCardsApp.entities.User;
import me.t.kaurami.giftCardsApp.repositories.UserRepository;
import me.t.kaurami.giftCardsApp.services.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import static me.t.kaurami.giftCardsApp.configs.security.authorities.UserRole.*;
import static me.t.kaurami.giftCardsApp.configs.security.authorities.UserRole.USER;

@Configuration
@Profile("dev")
public class DevSecurityConfig {

    @Autowired
    private UserService userService;

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, PasswordEncoder passwordEncoder,
                                        @Value("${admin.username}")String username, @Value("${admin.password}")String password) {
        return args -> {
            userRepository.save(
                    User.create(username, passwordEncoder.encode(password)).withRoles(USER, ADMIN, SERVICE_ENGINIER   ).build());
            userRepository.save(
                    User.create("username", passwordEncoder.encode("password")).withRoles(USER).build());
            userRepository.save(
                    User.create("username2", passwordEncoder.encode("password")).withRoles(USER).build());
        };
    }

}
