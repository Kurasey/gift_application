package me.t.kaurami.giftCardsApp.configs.security.authorities;

import org.springframework.security.core.GrantedAuthority;

public enum AdminAuthority implements GrantedAuthority {
    ACCESS_ADMIN_MODULE;

    @Override
    public String getAuthority() {
        return this.toString();
    }
}