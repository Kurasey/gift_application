package me.t.kaurami.giftCardsApp.controllers.categories;

import jakarta.validation.Valid;
import me.t.kaurami.giftCardsApp.entities.User;
import me.t.kaurami.giftCardsApp.controllers.annotations.ViewMenuItem;
import me.t.kaurami.giftCardsApp.entities.Category;
import me.t.kaurami.giftCardsApp.repositories.CategoryRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("categories")
@ViewMenuItem(controllerClass = CategoryController.class, title = "menuitem.categories", order = 2)
public class CategoryController{

    CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public String getForm() {
        return "categoryTable";
    }

    @PostMapping (params = "deleteCategoryId")
    public String deleteCategory(@RequestParam ("deleteCategoryId") Long id) {
        categoryRepository.deleteById(id);
        return "redirect:/categories";
    }
    @PostMapping (params = "changeCategoryId")
    public String changeCategory(@RequestParam ("changeCategoryId") Long id) {
        return "redirect:/categories/create?id=" + id;
    }

    @GetMapping (params = "create")
    public String createCategory() {
        return "redirect:/categories/create";
    }

    @GetMapping("/create")
    public String getFormNewCategory() {
        return "newCategory";
    }

    @GetMapping(value = "/create", params = "id")
    public String getFormNewCategory(@RequestParam("id") Long id, Model model) {
        model.addAttribute("category", categoryRepository.findById(id));
        return "newCategory";
    }

    @PostMapping("/create")
    public String addCategory(@Valid @ModelAttribute Category category, Errors errors) {
        if (errors.hasErrors()) {
            return "newCategory";
        }
        categoryRepository.save(category);
        return "redirect:/categories";
    }
    @PostMapping(path = "/create", params = "id")
    public String addCategory(@RequestParam("id") Long id, @Valid Category category, Errors errors) {
        if (errors.hasErrors()) {
            return "newCategory";
        }
        category.setId(id);
        categoryRepository.save(category);
        return "redirect:/categories";
    }

    @ModelAttribute("category")
    public Category getCategory(@AuthenticationPrincipal User user) {
        return Category.withUser(user);
    }

    @ModelAttribute ("categories")
    private List<Category> fillCategory(@AuthenticationPrincipal User currentUser) {
        return categoryRepository.findByUser(currentUser);
    }
}
