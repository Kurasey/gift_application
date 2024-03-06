package me.t.kaurami.giftCardsApp.controllers.gifts;

import me.t.kaurami.giftCardsApp.entities.User;
import me.t.kaurami.giftCardsApp.controllers.annotations.ViewMenuItem;
import me.t.kaurami.giftCardsApp.entities.BookingDetails;
import me.t.kaurami.giftCardsApp.entities.GiftDetail;
import me.t.kaurami.giftCardsApp.entities.GiftDetailView;
import me.t.kaurami.giftCardsApp.services.bookingservice.*;
import me.t.kaurami.giftCardsApp.services.giftservice.GiftService;
import me.t.kaurami.giftCardsApp.entities.SubscriptionDetails;
import me.t.kaurami.giftCardsApp.services.subscribeservice.SubscribeService;
import me.t.kaurami.giftCardsApp.services.userservice.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("gifts")
@ViewMenuItem(controllerClass = GiftTableController.class, title = "menuitem.gifts", order = 1)
public class GiftTableController{

    private GiftService giftService;
    private SubscribeService subscribeService;
    private UserService userService;
    private BookingService bookingService;


    public GiftTableController(GiftService giftService, SubscribeService subscribeService, UserService userService, BookingService bookingService) {
        this.giftService = giftService;
        this.subscribeService = subscribeService;
        this.userService = userService;
        this.bookingService = bookingService;
    }

    @GetMapping
    public String getGifts(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("giftList", giftService.getCurrentUserGifts(user));
        return "giftTable";
    }

    @GetMapping("/{username}")
    public String getGiftsByUser(@PathVariable("username") String username, @AuthenticationPrincipal User user, Model model) {
        SubscriptionDetails subscribeDetails = subscribeService.getSubscribeInfo(user ,userService.findByUsername(username));
        List<GiftDetailView> giftDetailViews = giftService.getGiftListBySubscription(subscribeDetails).stream().map(g->{
            switch (bookingService.getBookingStatus(g, subscribeDetails.getSubscriber())) {
                case NO_BOOKED:
                    return new GiftDetailView(g, false);
                case BOOKED_BY_ME:
                case BOOKED_ANOTHER:
                default:

                    return null;
            }
        }).filter(o -> o != null).collect(Collectors.toList());
        bookingService.getBookingDetailsBySubscription(subscribeDetails).stream().forEach(gd-> giftDetailViews.add(new GiftDetailView(gd.getGiftDetail(), true)));
        giftDetailViews.sort((o1, o2) -> o1.getGiftDetail().getGiftId().compareTo(o2.getGiftDetail().getGiftId()));
        model.addAttribute("username", username);
        model.addAttribute("giftDetailViews", giftDetailViews);
        model.addAttribute("availableCategories", subscribeDetails.getAvailableCategories());
        return "giftTableByUser";
    }

    @PostMapping(params = "deleteGiftId")
    public String deleteGift(@RequestParam("deleteGiftId") Long giftCardId) {
        giftService.deleteGift(giftCardId);
        return "redirect:/gifts";
    }

    @PostMapping(params = "updateGiftId")
    public String pathGift(@RequestParam("updateGiftId") Long giftCardId) {
        return "redirect:/gifts/create?id=" + giftCardId;
    }

    @PostMapping(path = "/{username}", params = "inFavoriteGiftId")
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public void AddFavoriteGift(@RequestParam("inFavoriteGiftId") Long inFavoriteGiftId) {
        throw new UnsupportedOperationException();
    }

    @PostMapping(path = "/{username}", params = "bookingGiftId")
    public String bookingGift(@RequestParam("bookingGiftId") Long bookingGiftId, @AuthenticationPrincipal User user) throws AlreadyBookedException, NotAvailableForBookingException {
        GiftDetail giftDetail = giftService.getById(bookingGiftId);
        SubscriptionDetails subscribeDetails = subscribeService.getSubscribeInfo(user, giftDetail.getUser());
        BookingDetails details = bookingService.bookGift(giftDetail, subscribeDetails);
        return "redirect:/gifts/" + subscribeDetails.getReceiver().getUsername();
    }
    @PostMapping(path = "/{username}", params = "unbookingGiftId")
    public String unbookingGift(@PathVariable("username")String usernam, @RequestParam("unbookingGiftId") Long unbookingGiftId, @AuthenticationPrincipal User user){
        bookingService.unbooking(bookingService.getBookingDetail(
                giftService.getById(unbookingGiftId)));
        return "redirect:/gifts/" + usernam;
    }
}
