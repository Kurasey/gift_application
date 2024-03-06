package me.t.kaurami.giftCardsApp.configs.security.authorities;

import org.springframework.security.core.GrantedAuthority;

public enum ServiceAuthority implements GrantedAuthority {
    ACCESS_DATA_REST;

    @Override
    public String getAuthority() {
        return this.toString();
    }
}
