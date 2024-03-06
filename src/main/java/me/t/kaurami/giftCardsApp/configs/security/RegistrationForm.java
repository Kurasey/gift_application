package me.t.kaurami.giftCardsApp.configs.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import me.t.kaurami.giftCardsApp.entities.User;
import me.t.kaurami.giftCardsApp.services.validators.UniqueUsername;
import org.springframework.security.crypto.password.PasswordEncoder;

public class RegistrationForm {

    @UniqueUsername
    @NotBlank(message = "Поле не может быть пустым")
    private String username;
    @NotBlank(message = "Поле не может быть пустым")
    private CharSequence password;

    @NotBlank(message = "Поле не может быть пустым")
    @Email(message = "Не корректный e-mail")
    private String email;

    public RegistrationForm (){}
    public RegistrationForm(String username, CharSequence password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User toUser(PasswordEncoder passwordEncoder) {
        return User.create(username, password).build();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public CharSequence getPassword() {
        return password;
    }

    public void setPassword(CharSequence password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

