package me.t.kaurami.giftCardsApp.configs.security.authorities;

import org.springframework.security.core.GrantedAuthority;

public enum UserManagamentAuthority implements GrantedAuthority {
    CREATE_USER, DELETE_USER, BLOCK_USER;

    @Override
    public String getAuthority() {
        return this.toString();
    }

}
