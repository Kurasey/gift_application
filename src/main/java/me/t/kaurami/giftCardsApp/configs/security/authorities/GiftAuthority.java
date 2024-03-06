package me.t.kaurami.giftCardsApp.configs.security.authorities;


import org.springframework.security.core.GrantedAuthority;

public enum GiftAuthority implements GrantedAuthority {
    CREATE_GIFT,
    READ_OWN_GIFT, EDIT_OWN_GIFT, DELETE_OWN_GIFT,
    READ_ALL_GIFT, EDIT_OTHER_GIFT;



    @Override
    public String getAuthority() {
        return this.toString();
    }
}
