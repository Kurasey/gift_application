package me.t.kaurami.giftCardsApp.configs.security;

import me.t.kaurami.giftCardsApp.services.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UserService userService;

    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userService);
        return provider;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception{
        return security
                .authorizeHttpRequests(
                        request -> request
                                .requestMatchers(
                                        AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/admin/**")).hasAuthority("ACCESS_ADMIN_MODULES")
                                .requestMatchers(
                                        AntPathRequestMatcher.antMatcher("/admin/data-api/**")).hasAuthority("ACCESS_DATA_REST")
                                .requestMatchers(
                                        AntPathRequestMatcher.antMatcher("/registration")).permitAll()
                                .requestMatchers(
                                        AntPathRequestMatcher.antMatcher("/login")).permitAll()
                                .requestMatchers(
                                        AntPathRequestMatcher.antMatcher("/styles/**")).permitAll()
                                .requestMatchers(
                                        AntPathRequestMatcher.antMatcher("/images/**")).permitAll()
                                .requestMatchers(
                                        AntPathRequestMatcher.antMatcher("/js/**")).permitAll()
                                .requestMatchers(
                                        AntPathRequestMatcher.antMatcher("/")).permitAll()
                                .anyRequest().authenticated())
                /*.httpBasic(Customizer.withDefaults())*/
                .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer
                        .loginPage("/login"))
                .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer
                        .logoutSuccessUrl("/"))
                .headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer.frameOptions().sameOrigin())
                .csrf().disable().build();

    }
}

