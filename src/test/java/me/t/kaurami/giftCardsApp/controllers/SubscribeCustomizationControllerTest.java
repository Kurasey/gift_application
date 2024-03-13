package me.t.kaurami.giftCardsApp.controllers;

import me.t.kaurami.giftCardsApp.entities.User;
import me.t.kaurami.giftCardsApp.controllers.subscribe.SubscribeCustomizationController;
import me.t.kaurami.giftCardsApp.repositories.CategoryRepository;
import me.t.kaurami.giftCardsApp.entities.SubscriptionDetails;
import me.t.kaurami.giftCardsApp.services.notificationservice.NotificationService;
import me.t.kaurami.giftCardsApp.services.subscribeservice.SubscribeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(SubscribeCustomizationController.class)
public class SubscribeCustomizationControllerTest {


    @MockBean
    NotificationService notificationService;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    SubscribeService subscribeService;
    @MockBean
    CategoryRepository categoryRepository;

    private String path = "/subscriptions/customize?id=1";

    @Test
    void unauthorizeGetMethodWithParamId() throws Exception {
        mockMvc.perform(get(path))
                .andExpect(status().is(401));
    }

    @Test
    @WithMockUser
    void authorizeGetMethodWithParamId() throws Exception {
        Mockito.when(subscribeService.getSubscribeInfo(1l)).thenReturn(
                new SubscriptionDetails(Mockito.mock(User.class), Mockito.mock(User.class), null)
        );
        mockMvc.perform(get(path))
                .andExpect(status().isOk())
                .andExpect(view().name("subscribeCustomization"));
    }

}