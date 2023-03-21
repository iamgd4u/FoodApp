package com.gourav.foodapp.controllers;

import com.gourav.foodapp.globaldata.GlobalData;
import com.gourav.foodapp.models.User;
import com.gourav.foodapp.repositories.UserRepository;
import org.apache.tomcat.jni.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LoginController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/mylogin")
    public String login(){
        return "mylogin";
    }

    @GetMapping("/custom")
    public String custom(Authentication auth){

        Optional<User> user = userRepository.findByUsername(auth.getName());

        if(user.isPresent()){
            GlobalData.currentUser = user.get();

            String role = auth.getAuthorities().toString();

            if(role.equals("[ROLE_ADMIN]")){
                return "redirect:/admin";
            }
            else if(role.equals("[ROLE_USER]")) {
                return "redirect:/menu";
            }
            else
                return "redirect:/";
        }
        else{
            User googleUser = new User();
            googleUser.setId(100);
            googleUser.setEmail(auth.getName());
            googleUser.setRole("USER");
            googleUser.setUsername(auth.getName());

            GlobalData.currentUser = googleUser;

            return "redirect:/menu";

        }



    }
}


