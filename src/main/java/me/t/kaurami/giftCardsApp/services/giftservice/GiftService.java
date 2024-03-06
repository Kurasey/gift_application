package me.t.kaurami.giftCardsApp.services.giftservice;

import me.t.kaurami.giftCardsApp.entities.User;
import me.t.kaurami.giftCardsApp.entities.GiftDetail;
import me.t.kaurami.giftCardsApp.entities.SubscriptionDetails;

import java.util.List;

public interface GiftService {

    public List<GiftDetail> getGiftListBySubscription(SubscriptionDetails subscribeDetails);

    public List<GiftDetail> getCurrentUserGifts(User user);

    public void deleteGift(Long giftId);

    public GiftDetail getById(Long detailId);

}
