package com.gourav.foodapp.controllers;

import com.gourav.foodapp.globaldata.GlobalData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String logout(){
        GlobalData.currentUser = null;
        GlobalData.isLoggedIn = false;
        return "index";
    }
}
