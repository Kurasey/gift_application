package me.t.kaurami.giftCardsApp.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import me.t.kaurami.giftCardsApp.configs.security.authorities.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "user-details", indexes = @Index(columnList = "username"))
public class User implements UserDetails, Comparable<User> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;
    @NotNull
    private String username;
    private String password;
    @Email
    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"))
    private List<UserRole> roles;
    private boolean accountNonExpired = true;
    private boolean enabled = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;

    public static class UserBuilder {

        private User userDetails;

        private UserBuilder(String username, CharSequence password) {
            userDetails = new User(username, password, UserRole.USER);
        }

        public UserBuilder withRoles(UserRole... userRoles){
            userDetails.roles = Arrays.stream(userRoles).toList();
            return this;
        }

        public User build(){
            return userDetails;
        }

    }

    public static UserBuilder create(String username, CharSequence password) {
        return new UserBuilder(username, password);
    }

    protected User() {
    }


    private User(String username, CharSequence password, UserRole userRole) {
        this.username = username;
        this.password = password.toString();
        this.roles = Arrays.asList(userRole);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(UserRole::getAuthorities).flatMap(Collection::stream).toList();

    }

    @Override
    public String getPassword() {
        return password.toString();
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("username='").append(username).append('\'');
        sb.append(", roles=").append(roles);
        sb.append(", accountNonExpired=").append(accountNonExpired);
        sb.append(", enabled=").append(enabled);
        sb.append(", accountNonLocked=").append(accountNonLocked);
        sb.append(", credentialsNonExpired=").append(credentialsNonExpired);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email);
    }

    @Override
    public int compareTo(User o) {
        return username.compareTo(o.username);
    }
}
