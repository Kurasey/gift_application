package me.t.kaurami.giftCardsApp.services.userservice;

import me.t.kaurami.giftCardsApp.entities.User;
import me.t.kaurami.giftCardsApp.entities.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> findUserByPartUsername(String partName);

    List<UserInfo> getUserInfo(User subscriber);

    List<User> findAll();

    User findByUsername(String username);
}
