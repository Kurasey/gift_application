package me.t.kaurami.giftCardsApp.repositories.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import me.t.kaurami.giftCardsApp.configs.security.authorities.UserRole;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<UserRole, String> {

    @Override
    public String convertToDatabaseColumn(UserRole userRole) {
        return userRole.toString();
    }

    @Override
    public UserRole convertToEntityAttribute(String s) {
        return UserRole.valueOf(s);
    }
}
