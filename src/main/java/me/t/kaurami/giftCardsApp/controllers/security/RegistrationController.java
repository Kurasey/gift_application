package me.t.kaurami.giftCardsApp.controllers.security;

import jakarta.validation.Valid;
import me.t.kaurami.giftCardsApp.repositories.UserRepository;
import me.t.kaurami.giftCardsApp.configs.security.RegistrationForm;
import me.t.kaurami.giftCardsApp.services.validators.ValidationGroup;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String getFormRegistration() {
        return "registration";
    }

    @PostMapping
    public String registrationProcess(@Valid @ModelAttribute RegistrationForm registrationForm, Errors errors) {
        System.err.println(1);
        if (errors.hasErrors()) {
            System.err.println(2);
            return "registration";
        }
        System.err.println(3);
        userRepository.save(registrationForm.toUser(passwordEncoder));
        System.err.println(4);
        return "redirect:/login";
    }

    @ModelAttribute("registrationForm")
    public RegistrationForm registrationForm() {
        return new RegistrationForm();
    }

}
