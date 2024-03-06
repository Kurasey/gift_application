package me.t.kaurami.giftCardsApp.services.bookingservice;

import me.t.kaurami.giftCardsApp.entities.User;
import me.t.kaurami.giftCardsApp.entities.BookingDetails;
import me.t.kaurami.giftCardsApp.entities.GiftDetail;
import me.t.kaurami.giftCardsApp.entities.SubscriptionDetails;

import java.util.List;

public interface BookingService {
    BookingDetails bookGift(GiftDetail giftDetail, SubscriptionDetails details) throws AlreadyBookedException, NotAvailableForBookingException;

    void unbooking(BookingDetails details);

    public List<BookingDetails> getBookingDetails(User user);

    public BookingDetails getBookingDetail(GiftDetail giftDetail);

    public BookingStatus getBookingStatus(GiftDetail giftDetail, User user);

    public List<BookingDetails> getBookingDetailsBySubscription(SubscriptionDetails subscribeDetails);

}
