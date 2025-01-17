package com.example.login_form.controller;

import com.example.login_form.model.UsersModel;
import com.example.login_form.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/register")
    public ModelAndView getRegisterPage() {
        ModelAndView modelAndView = new ModelAndView("register_page");
        modelAndView.addObject("registerRequest", new UsersModel());
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView getLoginPage() {
        ModelAndView modelAndView = new ModelAndView("login_page");
        modelAndView.addObject("loginRequest", new UsersModel());
        return modelAndView;
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UsersModel usersModel) {
        UsersModel registeredUser = usersService.registerUser(
                usersModel.getLogin(),
                usersModel.getPassword(),
                usersModel.getEmail()
        );
        if (registeredUser == null) {
            return "error_page";
        }
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UsersModel usersModel, Model model) {
        UsersModel authenticated = usersService.authenticate(
                usersModel.getLogin(),
                usersModel.getPassword()
        );

        if (authenticated != null) {
            model.addAttribute("currentUser", authenticated);
            return "redirect:/restaurant";
        } else {
            model.addAttribute("error", "Invalid login or password");
            return "error_page";
        }
    }

    @GetMapping("/restaurant")
    public String getRestaurantPage() {
        return "restaurant_page";
    }
}
