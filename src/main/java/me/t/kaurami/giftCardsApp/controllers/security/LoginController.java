package me.t.kaurami.giftCardsApp.controllers.security;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String showLoginForm() {
        return "login";
    }

    @GetMapping(params = "error")
    public String showErrorLoginForm(Model model) {
        System.err.println("error");
        model.addAttribute("error", true);
        return "login";
    }
}
