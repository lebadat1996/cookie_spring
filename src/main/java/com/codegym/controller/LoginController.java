package com.codegym.controller;

import com.codegym.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class LoginController {
    @GetMapping
    public ModelAndView show(@CookieValue(value = "email", defaultValue = "") String email,
                             @CookieValue(value = "password", defaultValue = "") String password) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("email", email);
        modelAndView.addObject("password", password);
        return modelAndView;
    }

    @PostMapping("login")
    public ModelAndView login(@ModelAttribute User user, @RequestParam(defaultValue = "") String rememberMe, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("result");
        if (rememberMe.equals("remember-me")) {
            Cookie saveEmail = new Cookie("email", user.getEmail());
            Cookie savePassword = new Cookie("password", user.getPassword());
            saveEmail.setMaxAge(60 * 2);
            savePassword.setMaxAge(60 * 3);
            response.addCookie(saveEmail);
            response.addCookie(savePassword);
        }
        return modelAndView;
    }
}
