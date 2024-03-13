package me.t.kaurami.giftCardsApp.services.giftservice;

import me.t.kaurami.giftCardsApp.entities.User;
import me.t.kaurami.giftCardsApp.entities.GiftDetail;
import me.t.kaurami.giftCardsApp.repositories.GiftRepository;
import me.t.kaurami.giftCardsApp.repositories.UserRepository;
import me.t.kaurami.giftCardsApp.entities.SubscriptionDetails;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class GiftServiceImpl implements GiftService {

    private GiftRepository giftRepository;


    public GiftServiceImpl(GiftRepository giftRepository, UserRepository userRepository) {
        this.giftRepository = giftRepository;
    }

    @Override
    public Iterable<GiftDetail> getGiftListByUser(User user) {
        return giftRepository.findByUser(user);
    }

    @Override
    @PreAuthorize("#giftCard.user.username.equals(authentication.principal.username)")
    public GiftDetail saveGift(GiftDetail giftCard) {
        return giftRepository.save(giftCard);
    }

    @Override
    @PreAuthorize("#user.username.equals(authentication.principal.username)")
    public List<GiftDetail> getCurrentUserGifts(User user) {
        return giftRepository.findByUser(user);
    }

    @Override
    @PreAuthorize("#details.subscriber.username.equals(authentication.principal.username)")
    public List<GiftDetail> getGiftListBySubscription(SubscriptionDetails details) {
        return giftRepository.findByCategoriesInAndRateBetween(
                details.getAvailableCategories(),
                details.getAvailableMinRate(),
                details.getAvailableMaxRate());
    }

    @Override
    public GiftDetail getById(Long detailId){
        Optional<GiftDetail> giftDetail = giftRepository.findById(detailId);
        if (giftDetail.isPresent())
            return giftDetail.get();
        throw new NoSuchElementException();
    }

    @Override
    public void deleteGift(Long giftId) {
        giftRepository.deleteById(giftId);
    }

}
