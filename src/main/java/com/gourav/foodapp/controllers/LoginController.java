package com.gourav.foodapp.controllers;

import com.gourav.foodapp.globaldata.GlobalData;
import com.gourav.foodapp.models.User;
import com.gourav.foodapp.repositories.UserRepository;
import org.apache.tomcat.jni.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/login")
    public String getLogin(Model model) {
        model.addAttribute("isLoggedIn", GlobalData.isLoggedIn);
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            System.out.println("returning error");
            return "redirect:login?error";
        } else {
            GlobalData.currentUser = user;
            GlobalData.isLoggedIn = true;
            model.addAttribute("isLoggedIn",GlobalData.isLoggedIn);
            if (user.getRole().equals("admin")) {
                return "redirect:/admin";
            } else {
                System.out.println("returning menu");
                return "redirect:/menu";
            }
            //return "menu";
        }

    }
}


