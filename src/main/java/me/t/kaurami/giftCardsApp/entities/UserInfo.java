package me.t.kaurami.giftCardsApp.entities;

public class UserInfo{

    private String username;
    private boolean hasSubscribe;

    public UserInfo(String username, boolean hasSubscribe) {
        this.username = username;
        this.hasSubscribe = hasSubscribe;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserInfo{");
        sb.append("username='").append(username).append('\'');
        sb.append(", hasSubscribe=").append(hasSubscribe);
        sb.append('}');
        return sb.toString();
    }

    public String getUsername() {
        return username;
    }

    public boolean isHasSubscribe() {
        return hasSubscribe;
    }
}