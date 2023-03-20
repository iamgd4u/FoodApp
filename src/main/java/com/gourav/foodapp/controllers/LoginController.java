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

    @GetMapping("/mylogin")
    public String login(){
        return "mylogin";
    }

    @PostMapping("/dologin")
    public void doLogin(){
        System.out.println("inside dologin");
    }
}


