/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uef.edu.vn.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uef.edu.vn.model.User;
import uef.edu.vn.service.UserService;

/**
 *
 * @author minhq
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginForm(
            Model model) {

        model.addAttribute(
                "user",
                new User()
        );

        return "auth/login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        User user = userService.login(
                username,
                password
        );

        if (user == null) {

            model.addAttribute(
                    "error",
                    "Invalid username or password"
            );
            model.addAttribute(
                    "user",
                    new User()
            );

            return "auth/login";
        }

        session.setAttribute(
                "loggedUser",
                user
        );

        return "redirect:/dashboard";
    }

    @GetMapping("/logout")
    public String logout(
            HttpSession session) {

        session.invalidate();

        return "redirect:/login";
    }

    @GetMapping("/register")
    public String registerForm(
            Model model) {

        model.addAttribute(
                "user",
                new User());

        return "auth/register";
    }

    @PostMapping("/register")
    public String register(
            @ModelAttribute User user) {

        userService.register(user);

        return "redirect:/login";
    }
}
