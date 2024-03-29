package me.t.kaurami.giftCardsApp.controllers.gifts;


import jakarta.validation.Valid;
import me.t.kaurami.giftCardsApp.entities.User;
import me.t.kaurami.giftCardsApp.entities.Category;
import me.t.kaurami.giftCardsApp.entities.GiftDetail;
import me.t.kaurami.giftCardsApp.repositories.CategoryRepository;
import me.t.kaurami.giftCardsApp.repositories.GiftRepository;
import me.t.kaurami.giftCardsApp.services.giftservice.GiftService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("gifts/create")
public class CreateGiftController {

    private GiftService giftService;
    private CategoryRepository categoryRepository;

    public CreateGiftController(GiftService giftService, CategoryRepository categoryRepository) {
        this.giftService = giftService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public String showForm() {
        return "newGift";
    }

    @GetMapping(params = "id")
    public String showChangeForm(@RequestParam("id") Long id, Model model) {
        GiftDetail giftDetail = giftService.getById(id);
        model.addAttribute("giftDetail", giftDetail);
        model.addAttribute("categories", giftDetail.getCategories());
        return "newGift";
    }

    @PostMapping
    public String addGiftCard(@Valid @ModelAttribute GiftDetail giftDetail, Errors errors) {
        if (errors.hasErrors()) {
            return "newGift";
        }
        giftService.saveGift(giftDetail);
        return "redirect:/gifts";
    }
    @PostMapping(params = "id")
    public String addGiftCard(@RequestParam("id") Long id, @Valid GiftDetail giftDetail, Errors errors) {
        if (errors.hasErrors()) {
            return "newGift";
        }
        giftDetail.setGiftId(id);
        giftService.saveGift(giftDetail);
        return "redirect:/gifts";
    }

    @ModelAttribute("giftDetail")
    private GiftDetail giftDetail(@AuthenticationPrincipal User user) {
        GiftDetail giftCard = new GiftDetail("");
        giftCard.setUser(user);
        return giftCard;
    }

    @ModelAttribute("availableCategories")
    private List<Category> categories (@AuthenticationPrincipal User currentUser){
        return categoryRepository.findByUser(currentUser);
    }
}
