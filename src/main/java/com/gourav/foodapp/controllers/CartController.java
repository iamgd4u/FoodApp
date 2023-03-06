package com.gourav.foodapp.controllers;

import com.gourav.foodapp.globaldata.GlobalData;
import com.gourav.foodapp.repositories.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CartController {
    @Autowired
    FoodRepository foodRepository;

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable int id){
        GlobalData.cart.add(foodRepository.findById(id).get());
        return "redirect:/menu";
    }

    @GetMapping("/cart")
    public String cart(Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("total", GlobalData.total());
        model.addAttribute("cart", GlobalData.cart);
        model.addAttribute("isLoggedIn",GlobalData.isLoggedIn);
        return "cart";
    }

    @GetMapping("/cart/removeItem/{index}")
    public String cartRemove(@PathVariable int index){
        GlobalData.cart.remove(index);
        return "redirect:/cart";
    }




}
