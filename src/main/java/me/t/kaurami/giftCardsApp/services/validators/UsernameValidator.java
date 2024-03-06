package me.t.kaurami.giftCardsApp.services.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import me.t.kaurami.giftCardsApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class UsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    UserRepository userRepository;

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return userRepository.findByUsername(value) == null ? true : false;
    }


}

