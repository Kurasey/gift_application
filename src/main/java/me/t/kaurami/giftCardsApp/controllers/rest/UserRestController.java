package me.t.kaurami.giftCardsApp.controllers.rest;

import me.t.kaurami.giftCardsApp.entities.User;
import me.t.kaurami.giftCardsApp.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/rest-api/user")
public class UserRestController {

    UserRepository userRepository;

    public UserRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(params = "all")
    public Iterable<User> userDetailsGiftApps() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id")Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent())
            return ResponseEntity.ok().body(user.get());
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username")String username) {
        User user = userRepository.findByUsername(username);
        if (user != null)
            return ResponseEntity.ok().body(user);
        return ResponseEntity.notFound().build();
    }
}
