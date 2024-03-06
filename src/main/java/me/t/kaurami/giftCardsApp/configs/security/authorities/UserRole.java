package me.t.kaurami.giftCardsApp.configs.security.authorities;

import jakarta.persistence.Entity;
import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.List;

import static me.t.kaurami.giftCardsApp.configs.security.authorities.GiftAuthority.*;


public enum UserRole {
    ADMIN(AdminAuthority.values() ,UserManagamentAuthority.values()),
    USER(CREATE_GIFT, READ_OWN_GIFT, EDIT_OWN_GIFT, DELETE_OWN_GIFT),
    MODERATOR(GiftAuthority.values()),
    SERVICE_ENGINIER(ServiceAuthority.values());

    List<GrantedAuthority> authorities;

    public List<GrantedAuthority> getAuthorities(){
        return authorities;
    }

    private UserRole(GrantedAuthority... authorities) {
        this.authorities = Arrays.stream(authorities).toList();
    }
    private UserRole(GrantedAuthority[]... authorities) {
        this.authorities = Arrays.stream(authorities).flatMap(Arrays::stream).toList();
    }

}