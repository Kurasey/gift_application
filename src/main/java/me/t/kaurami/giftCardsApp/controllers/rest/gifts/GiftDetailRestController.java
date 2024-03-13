package me.t.kaurami.giftCardsApp.controllers.rest.gifts;

import me.t.kaurami.giftCardsApp.entities.User;
import me.t.kaurami.giftCardsApp.entities.GiftDetail;
import me.t.kaurami.giftCardsApp.repositories.GiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/gift", consumes = "application/json", produces = "application/json")
public class GiftDetailRestController {

    @Autowired
    private GiftRepository giftRepository;

    @GetMapping("/{id}")
    public ResponseEntity<GiftDetail> getGiftById(@PathVariable("id") Long id) {
        Optional<GiftDetail> giftDetail = giftRepository.findById(id);
        if (giftDetail.isPresent()) {
            return ResponseEntity.ok(giftDetail.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{user}")
    public ResponseEntity<Iterable<GiftDetail>> getGiftByUser(@RequestBody User user) {
        Iterable<GiftDetail> giftDetails = giftRepository.findByUser(user);
        if (giftDetails != null) {
            return ResponseEntity.ok().body(giftDetails);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void saveGiftCard(@RequestBody GiftDetail giftDetail) {
        giftRepository.save(giftDetail);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('DELETE_OTHER_GIFT') || #giftDetail.user.userId == authentication.userId")
    public void deleteGift(@RequestBody GiftDetail giftDetail) {
        giftRepository.delete(giftDetail);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody GiftDetail giftDetail) {
        giftRepository.save(giftDetail);
    }
}
