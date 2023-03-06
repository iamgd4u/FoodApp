package com.gourav.foodapp.controllers;

import com.gourav.foodapp.globaldata.GlobalData;
import com.gourav.foodapp.models.Food;
import com.gourav.foodapp.models.CustomerOrder;
import com.gourav.foodapp.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class OrderController {
    @Autowired
    OrderRepository orderRepository;

    private int turn = 1;

    @GetMapping("/placeOrder")
    public String placeOrder(Model model){
        if(!GlobalData.isLoggedIn)
            return "redirect:/login?errorLogIn";
        else{
            model.addAttribute("isLoggedIn",GlobalData.isLoggedIn);
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

    @GetMapping("/viewOrders")
    public String viewOrders(Model model){
        List<CustomerOrder> orders = orderRepository.findMyOrders(GlobalData.currentUser.getId());
        model.addAttribute("isLoggedIn",GlobalData.isLoggedIn);
        model.addAttribute("orders",orders);

        return "myOrders";

    }
    @GetMapping("/order/delete/{id}")
    public String deleteOrder(@PathVariable int id, Model model){
        model.addAttribute("isLoggedIn",GlobalData.isLoggedIn);
        if(orderRepository.findById(id).isPresent()){
            System.out.println("deleting"+id);
            orderRepository.deleteById(id);
        }

        return "redirect:/viewOrders";
    }

}
