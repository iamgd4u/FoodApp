package com.gourav.foodapp.controllers;

import com.gourav.foodapp.globaldata.GlobalData;
import com.gourav.foodapp.models.Food;
import com.gourav.foodapp.models.CustomerOrder;
import com.gourav.foodapp.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {
    @Autowired
    OrderRepository orderRepository;

    private int turn = 1;

    @GetMapping("/placeOrder")
    public String placeOrder(Model model){
        model.addAttribute("cart", GlobalData.cart);
        model.addAttribute("total",GlobalData.total());
        //add to order table
        System.out.println(GlobalData.cart);
        if(turn == 1){
            for(Food f : GlobalData.cart){
                CustomerOrder o = new CustomerOrder(0,GlobalData.currentUser.getId(), f.getId());
                orderRepository.save(o);
            }
            turn = 0;
        }


        return "orderPlaced";
    }
}
