package me.t.kaurami.giftCardsApp.repositories;

import me.t.kaurami.giftCardsApp.entities.User;
import me.t.kaurami.giftCardsApp.entities.GiftDetail;
import me.t.kaurami.giftCardsApp.entities.BookingDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingDetailsRepository extends JpaRepository<BookingDetails, Long> {

    public Optional<BookingDetails> findByGiftDetail(GiftDetail giftDetail);

    public List<BookingDetails> findByBookedBy(User bookedBy);

    public List<BookingDetails> findByBookedByAndGiftUser(User bookedBy, User giftUser);
}
