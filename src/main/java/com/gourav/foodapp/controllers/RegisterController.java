package com.gourav.foodapp.controllers;

import com.gourav.foodapp.globaldata.GlobalData;
import com.gourav.foodapp.models.User;
import com.gourav.foodapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user",new User());
        return "register";
    }
    @PostMapping("/register")
    public String postRegister(@ModelAttribute("user") User user,Model model){
        userRepository.save(user);
        GlobalData.currentUser = user;
        GlobalData.isLoggedIn = true;
        model.addAttribute("isLoggedIn",GlobalData.isLoggedIn);
        if (user.getRole().equals("admin")) {
            return "redirect:/admin";
        } else {
            System.out.println("returning menu");
            return "redirect:/menu";
        }

    }
}
