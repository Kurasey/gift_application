package me.t.kaurami.giftCardsApp.controllers;

import me.t.kaurami.giftCardsApp.entities.User;
import me.t.kaurami.giftCardsApp.controllers.gifts.GiftTableController;
import me.t.kaurami.giftCardsApp.entities.Category;
import me.t.kaurami.giftCardsApp.entities.GiftDetail;
import me.t.kaurami.giftCardsApp.services.bookingservice.BookingService;
import me.t.kaurami.giftCardsApp.services.bookingservice.BookingStatus;
import me.t.kaurami.giftCardsApp.services.giftservice.GiftService;
import me.t.kaurami.giftCardsApp.entities.SubscriptionDetails;
import me.t.kaurami.giftCardsApp.services.subscribeservice.SubscribeService;
import me.t.kaurami.giftCardsApp.services.userservice.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@WebMvcTest(GiftTableController.class)
public class GiftTableControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @MockBean
    SubscribeService subscribeService;

    @MockBean
    GiftService giftService;

    @MockBean
    BookingService bookingService;

    Category availableCat1;
    Category notAvailableCat;

    @Test
    @WithMockUser
    void whenShowMyGiftFormWithAuthorizationThenTrue() throws Exception{
        Assertions.assertEquals(giftService.getCurrentUserGifts(mock(User.class)).size(), 10);
        mockMvc.perform(get("/gifts"))
                .andExpect(status().isOk())
                .andExpect(view().name("giftTable"))
                .andExpect(content().string(containsString("Test gift")));;
    }

    @Test
    @WithMockUser
    void whenShowUserGiftFormWithAuthorizationThenTrue() throws Exception{
        Assertions.assertEquals(giftService.getGiftListBySubscription(mock(SubscriptionDetails.class)).size(), 10);
        mockMvc.perform(get("/gifts/testuser"))
                .andExpect(status().isOk())
                .andExpect(view().name("giftTableByUser"))
                .andExpect(content().string(containsString(availableCat1.getDescription())))
                .andExpect(content().string(not(containsString(notAvailableCat.getDescription()))));
    }

    @Test
    void whenShowMyGiftsWithoutAuthorizationThenStatus401() throws Exception {
        mockMvc.perform(get("/gifts")).andExpect(status().is(401));
    }
    @Test
    void whenShowUserGiftsWithoutAuthorizationThenStatus401() throws Exception {
        mockMvc.perform(get("/gifts")).andExpect(status().is(401));
    }

    @BeforeEach
    void setup() {
        availableCat1 = mock(Category.class);
        notAvailableCat = mock(Category.class);
        when(availableCat1.getDescription()).thenReturn("Available test category");
        when(notAvailableCat.getDescription()).thenReturn("Not available test category");


        List<GiftDetail> giftCards = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            giftCards.add(mock(GiftDetail.class));
            giftCards.get(giftCards.size()-1).setRate(i);
        }

        for (GiftDetail giftCard : giftCards) {
            when(giftCard.getArticleNumber()).thenReturn("11231");
            when(giftCard.getCommentary()).thenReturn("comment");
            when(giftCard.getDescription()).thenReturn("Test gift");
            when(giftCard.getCategories()).thenReturn(new Category[]{availableCat1, notAvailableCat});
        }

        SubscriptionDetails details = mock(SubscriptionDetails.class);
        when(details.getAvailableCategories()).thenReturn((Arrays.asList(availableCat1)));

        when(userService.findByUsername(any())).thenReturn(mock(User.class));
        when(subscribeService.getSubscribeInfo(any(), any())).thenReturn(details);
        when(giftService.getGiftListBySubscription(any())).thenReturn(giftCards);
        when(giftService.getCurrentUserGifts(any())).thenReturn(giftCards);
        when(bookingService.getBookingStatus(any(), any())).thenReturn(BookingStatus.NO_BOOKED);
    }
}
