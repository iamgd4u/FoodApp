package com.gourav.foodapp.controllers;

import com.gourav.foodapp.dto.CustomOrder;
import com.gourav.foodapp.globaldata.GlobalData;
import com.gourav.foodapp.models.Food;
import com.gourav.foodapp.models.CustomerOrder;
import com.gourav.foodapp.repositories.FoodRepository;
import com.gourav.foodapp.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    FoodRepository foodRepository;

    private int turn = 1;
    //private int id = 0;

    @GetMapping("/placeOrder")
    public String placeOrder(Model model, Authentication auth){

        if(!auth.isAuthenticated())
            return "redirect:/login?errorLogIn";
        else{
            model.addAttribute("isLoggedIn",GlobalData.isLoggedIn);
            model.addAttribute("cart", GlobalData.cart);
            model.addAttribute("total",GlobalData.total());
            //add to order table
            System.out.println(GlobalData.cart);
            for (Food f : GlobalData.cart) {
                //int id = 0;
                CustomerOrder o = new CustomerOrder(0, GlobalData.currentUser.getId(), f.getId());
                orderRepository.save(o);

            }
            return "orderPlaced";
        }
    }

    @GetMapping("/viewOrders")
    public String viewOrders(Model model){
        List<CustomerOrder> customerOrders = orderRepository.findMyOrders(GlobalData.currentUser.getId());
        List<CustomOrder> orders = new ArrayList<>();

        for(CustomerOrder customerOrder : customerOrders){
            CustomOrder customOrder = new CustomOrder();
            customOrder.setId(customerOrder.getId());
            customOrder.setUserId(customerOrder.getUser_id());
            customOrder.setFoodId(customerOrder.getFood_id());
            Food food = foodRepository.findById(customerOrder.getFood_id()).get();
            customOrder.setFoodName(food.getName());
            orders.add(customOrder);
        }

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
