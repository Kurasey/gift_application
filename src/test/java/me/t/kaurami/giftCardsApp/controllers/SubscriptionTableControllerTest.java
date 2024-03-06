package me.t.kaurami.giftCardsApp.controllers;


import me.t.kaurami.giftCardsApp.controllers.subscribe.SubscriptionTableController;
import me.t.kaurami.giftCardsApp.entities.UserInfo;
import me.t.kaurami.giftCardsApp.services.bookingservice.BookingService;
import me.t.kaurami.giftCardsApp.services.subscribeservice.SubscribeService;
import me.t.kaurami.giftCardsApp.services.userservice.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SubscriptionTableController.class)
@ActiveProfiles("test")
public class SubscriptionTableControllerTest {

    @MockBean
    UserService userService;

    @MockBean
    SubscribeService subscribeService;

    @MockBean
    BookingService bookingService;

    @Autowired
    MockMvc mockMvc;


    @Test
    @WithMockUser
    public void getForm() throws Exception{
        Mockito.when(userService.getUserInfo(any())).thenReturn(Arrays.asList(new UserInfo("user1", false)));

        mockMvc.perform(get("/subscriptions"))
                .andExpect(status().isOk())
                .andExpect(view().name("subscriptionsTable"));
    }

}
