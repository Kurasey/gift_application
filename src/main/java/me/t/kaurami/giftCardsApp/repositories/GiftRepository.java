package me.t.kaurami.giftCardsApp.repositories;


import me.t.kaurami.giftCardsApp.entities.User;
import me.t.kaurami.giftCardsApp.entities.Category;
import me.t.kaurami.giftCardsApp.entities.GiftDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GiftRepository extends JpaRepository<GiftDetail, Long> {

    public List<GiftDetail> findByUser(User user);

    public List<GiftDetail> findByCategoriesInAndRateBetween(List<Category> categories, int rateFrom, int rateTo);


}
