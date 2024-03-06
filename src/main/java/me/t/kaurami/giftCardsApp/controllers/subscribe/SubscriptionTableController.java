package me.t.kaurami.giftCardsApp.controllers.subscribe;

import me.t.kaurami.giftCardsApp.entities.User;
import me.t.kaurami.giftCardsApp.controllers.annotations.ViewMenuItem;
import me.t.kaurami.giftCardsApp.entities.UserInfo;
import me.t.kaurami.giftCardsApp.services.bookingservice.BookingService;
import me.t.kaurami.giftCardsApp.entities.SubscriptionDetails;
import me.t.kaurami.giftCardsApp.services.subscribeservice.SubscribeService;
import me.t.kaurami.giftCardsApp.services.userservice.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("subscriptions")
@ViewMenuItem(controllerClass = SubscriptionTableController.class, title = "menuitem.users", order = 3)
public class SubscriptionTableController {

    private UserService userService;
    private SubscribeService subscribeService;
    private BookingService bookingService;


    public SubscriptionTableController(UserService userService, SubscribeService subscribeService, BookingService bookingService) {
        this.userService = userService;
        this.subscribeService = subscribeService;
        this.bookingService = bookingService;
    }

    @GetMapping()
    public String showSubscriptions() {
        return "subscriptionsTable";
    }

    @PostMapping(params = {"username"})
    public String initialSubscribe(@AuthenticationPrincipal User subscriber, @RequestParam("username") String username) {
        subscribeService.initiateRequest(subscriber, userService.findByUsername(username));
        return "redirect:/subscriptions";
    }

    @PostMapping(params = {"cancelSubscription"})
    public String cancelSubscription(@AuthenticationPrincipal User subscriber, @RequestParam("cancelSubscription") String username) {
        subscribeService.cancelSubscription(subscriber, userService.findByUsername(username));
        return "redirect:/subscriptions";
    }

    @PostMapping(params = "customizeSubscribe")
    public String customizeSubscribe(@RequestParam("customizeSubscribe") String username, @AuthenticationPrincipal User user) {
        SubscriptionDetails details = subscribeService.getSubscribeInfo(userService.findByUsername(username), user);
        return "redirect:/subscriptions/customize?id=" + details.getId();
    }

    @PostMapping(params = "userGifts")
    public String showUserGift(@RequestParam("userGifts") String username) {
        return "redirect:/gifts/" + username;
    }

    @ModelAttribute("usersInfo")
    private List<UserInfo> getUserInfo(@AuthenticationPrincipal User user) {
        return userService.getUserInfo(user);
    }

    @ModelAttribute("subscribers")
    private List<User> subscribers(@AuthenticationPrincipal User user) {
        return subscribeService.getSubscribers(user);
    }

    @ModelAttribute("subscriptions")
    private List<User> subscriptions(@AuthenticationPrincipal User user) {
        return subscribeService.getSubscriptions(user);
    }
}
