package me.t.kaurami.giftCardsApp.controllers;

import me.t.kaurami.giftCardsApp.controllers.gifts.CreateGiftController;
import me.t.kaurami.giftCardsApp.entities.Category;
import me.t.kaurami.giftCardsApp.entities.GiftDetailBuilder;
import me.t.kaurami.giftCardsApp.repositories.CategoryRepository;
import me.t.kaurami.giftCardsApp.repositories.GiftRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Locale;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

@ActiveProfiles("test")
@WebMvcTest(CreateGiftController.class)
public class CreateGiftControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    GiftRepository giftRepository;

    @MockBean
    CategoryRepository categoryRepository;


    @BeforeAll
    static void setupLocale() {
        Locale.setDefault(new Locale("ru"));
        LocaleContextHolder.setDefaultLocale(new Locale("ru"));
    }

    @Test
    @WithMockUser
    public void getForm() throws Exception {
        mockMvc.perform(get("/gifts/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("newGift"))
                .andExpect(content().string(Matchers.containsString("add")));
    }

    @Test
    @WithMockUser
    public void getFormWithId() throws Exception {
        Mockito.when(giftRepository.findById(1l)).thenReturn(Optional.of(new GiftDetailBuilder()
                .description("MyGift")
                .articleNumber("11122211")
                .rate(8)
                .link("www.gift.org")
                .commentary("Test gift")
//                .categories(new Category("Test categoryfuhj"))
                .build()));
        mockMvc.perform(get("/gifts/create?id=1"))
                .andExpect(status().isOk())
                .andExpect(view().name("newGift"))
                .andExpect(content().string(Matchers.containsString("www.gift.org")));
    }

    @Test
    @WithMockUser
    public void postMethod() throws Exception{
        mockMvc.perform(
                post("/gifts/create").with(csrf())
                        .requestAttr("giftCard", new GiftDetailBuilder()
                                .description("MyGift")
                                .articleNumber("11122211")
                                .rate(8)
                                .link("www.gift.org")
                                .commentary("Test gift")
                                .categories(Category.withDescription("Test category"))
                                .build()))
                .andExpect(status().isOk());
    }
}
