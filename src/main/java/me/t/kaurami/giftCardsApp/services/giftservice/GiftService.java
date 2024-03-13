package me.t.kaurami.giftCardsApp.services.giftservice;

import me.t.kaurami.giftCardsApp.entities.User;
import me.t.kaurami.giftCardsApp.entities.GiftDetail;
import me.t.kaurami.giftCardsApp.entities.SubscriptionDetails;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface GiftService {

    public List<GiftDetail> getGiftListBySubscription(SubscriptionDetails subscribeDetails);

    Iterable<GiftDetail> getGiftListByUser(User user);

    GiftDetail saveGift(GiftDetail giftCard);

    public List<GiftDetail> getCurrentUserGifts(User user);

    public void deleteGift(Long giftId);

    public GiftDetail getById(Long detailId);

}
