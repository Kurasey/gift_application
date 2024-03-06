package me.t.kaurami.giftCardsApp.services.userservice;

import me.t.kaurami.giftCardsApp.entities.User;
import me.t.kaurami.giftCardsApp.entities.UserInfo;
import me.t.kaurami.giftCardsApp.repositories.SubscribeRepository;
import me.t.kaurami.giftCardsApp.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StandardUserService implements UserService {

    private UserRepository userRepository;
    private SubscribeRepository subscribeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException(String.format("User with username '%s' not found", username));
        return user;
    }

    public StandardUserService(UserRepository userRepository, SubscribeRepository subscribeRepository) {
        this.userRepository = userRepository;
        this.subscribeRepository = subscribeRepository;
    }

    @Override
    public List<User> findUserByPartUsername(String partName){
        List<User> users = userRepository.findByUsernameContainingIgnoringCase(partName);
        return users;
    }

    @Override
    public List<UserInfo> getUserInfo(User subscriber) {
        return userRepository.findAll().stream().filter(u -> !u.equals(subscriber)).map(u->
                new UserInfo(u.getUsername(), subscribeRepository.findBySubscriberAndReceiver(subscriber, u).isPresent()))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }


    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}


