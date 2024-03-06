package me.t.kaurami.giftCardsApp.controllers.subscribe;

import jakarta.validation.Valid;
import me.t.kaurami.giftCardsApp.entities.User;
import me.t.kaurami.giftCardsApp.entities.Category;
import me.t.kaurami.giftCardsApp.repositories.CategoryRepository;
import me.t.kaurami.giftCardsApp.entities.SubscriptionDetails;
import me.t.kaurami.giftCardsApp.services.subscribeservice.SubscribeService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/subscriptions/customize")
public class SubscribeCustomizationController {

    SubscribeService subscribeService;
    private CategoryRepository categoryRepository;

    public SubscribeCustomizationController(SubscribeService subscribeService, CategoryRepository categoryRepository) {
        this.subscribeService = subscribeService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping(params = "id")
    public String getForm(@RequestParam("id")Long id, Model model) {
        model.addAttribute("details", subscribeService.getSubscribeInfo(id));
        return "subscribeCustomization";
    }

    @PostMapping(params = "id")
    public String customizeSubscribe(@RequestParam("id") Long id, @Valid @ModelAttribute("details") SubscriptionDetails details, Errors errors) throws IllegalAccessException {
        if (errors.hasErrors()) {
            return "subscribeCustomization";
        }
        subscribeService.customizeSubscribe(details);
        return "redirect:/subscriptions";
    }

    @ModelAttribute("userCategories")
    public List<Category> addCategoriesToModel(@AuthenticationPrincipal User user) {
        return categoryRepository.findByUser(user);
    }
}
