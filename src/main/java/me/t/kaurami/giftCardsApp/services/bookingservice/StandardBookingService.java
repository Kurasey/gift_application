package me.t.kaurami.giftCardsApp.services.bookingservice;

import jakarta.transaction.Transactional;
import me.t.kaurami.giftCardsApp.entities.User;
import me.t.kaurami.giftCardsApp.entities.BookingDetails;
import me.t.kaurami.giftCardsApp.entities.GiftDetail;
import me.t.kaurami.giftCardsApp.repositories.BookingDetailsRepository;
import me.t.kaurami.giftCardsApp.entities.SubscriptionDetails;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class StandardBookingService implements BookingService {

    private BookingDetailsRepository bookingDetailsRepository;

    public StandardBookingService(BookingDetailsRepository bookingDetailsRepository) {
        this.bookingDetailsRepository = bookingDetailsRepository;
    }

    @PreAuthorize("#subscribeDetails.subscriber.username.equals(authentication.principal.username)")
    @Transactional
    @Override
    public BookingDetails bookGift(GiftDetail giftDetail, SubscriptionDetails subscribeDetails) throws AlreadyBookedException, NotAvailableForBookingException{
        if (!getBookingStatus(giftDetail, subscribeDetails.getSubscriber()).equals(BookingStatus.NO_BOOKED)){
            throw new AlreadyBookedException();
        }
        if (Arrays.stream(giftDetail.getCategories()).filter(category -> subscribeDetails.getAvailableCategories().contains(category)).count()!=0
                && giftDetail.getRate() >= subscribeDetails.getAvailableMinRate()
                && giftDetail.getRate() <= subscribeDetails.getAvailableMaxRate()){
            return bookingDetailsRepository.save(new BookingDetails(giftDetail, subscribeDetails.getSubscriber(), LocalDate.now()));
        }
        throw new NotAvailableForBookingException();
    }

    @PreAuthorize("#details.bookedBy.username.equals(authentication.principal.username)")
    @Override
    public void unbooking(BookingDetails details) {
        bookingDetailsRepository.delete(details);
    }

    @PreAuthorize("#user.username.equals(authentication.principal.username)")
    public List<BookingDetails> getBookingDetails(User user) {
        return bookingDetailsRepository.findByBookedBy(user);
    }

    @PostAuthorize("returnObject.bookedBy.username.equals(authentication.principal.username)")
    public BookingDetails getBookingDetail(GiftDetail giftDetail) {
        return bookingDetailsRepository.findByGiftDetail(giftDetail).get();
    }

    public BookingStatus getBookingStatus(GiftDetail giftDetail, User user){
        Optional<BookingDetails> details = bookingDetailsRepository.findByGiftDetail(giftDetail);
        if (details.isPresent()){
            if (details.get().getBookedBy().equals(user))
                return BookingStatus.BOOKED_BY_ME;
            return BookingStatus.BOOKED_ANOTHER;
        }
        return BookingStatus.NO_BOOKED;
    }

    @Override
    public List<BookingDetails> getBookingDetailsBySubscription(SubscriptionDetails subscribeDetails){
        return bookingDetailsRepository.findByBookedByAndGiftUser(subscribeDetails.getSubscriber(), subscribeDetails.getReceiver());
    }

}
     