package com.gourav.foodapp.controllers;

import com.gourav.foodapp.globaldata.GlobalData;
import com.gourav.foodapp.repositories.FoodRepository;
import org.apache.tomcat.jni.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
    @Autowired
    FoodRepository foodRepository;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("isLoggedIn", GlobalData.isLoggedIn);
        return "index";
    }
    @GetMapping("/menu")
    public String menu(Model model){
        model.addAttribute("foods",foodRepository.findAll());
        model.addAttribute("isLoggedIn", GlobalData.isLoggedIn);
        return "menu";
    }
    @GetMapping("/shop/viewproduct/{id}")
    public String viewProduct(@PathVariable int id, Model model){
        model.addAttribute("product",foodRepository.findById(id).get());

        return "viewProduct";
    }
}
