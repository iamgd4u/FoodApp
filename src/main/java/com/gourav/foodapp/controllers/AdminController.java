package com.gourav.foodapp.controllers;

import com.gourav.foodapp.models.Food;
import com.gourav.foodapp.models.User;
import com.gourav.foodapp.repositories.FoodRepository;
import com.gourav.foodapp.repositories.OrderRepository;
import com.gourav.foodapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {
    @Autowired
    FoodRepository foodRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderRepository orderRepository;
    @GetMapping("/admin")
    public String adminHome(){
        return "adminHome";
    }
    @GetMapping("admin/food")
    public String adminFood(Model model){
          model.addAttribute("foods",foodRepository.findAll());
        return "food";
    }

    @GetMapping("admin/food/add")
    public String adminFoodAdd(Model model){
        model.addAttribute("food", new Food());
        return "foodAdd";
    }

    @PostMapping("admin/food/add")
    public String adminPostFood(@ModelAttribute("food") Food food){
        foodRepository.save(food);
        return "redirect:/admin/food";
    }

    @GetMapping("/admin/food/delete/{id}")
    public String delete(@PathVariable("id") int id){
        foodRepository.deleteById(id);
        return "redirect:/admin/food";
    }
    @GetMapping("/admin/food/update/{id}")
    public String update(@PathVariable int id, Model model){
        Optional<Food> food = foodRepository.findById(id);
        model.addAttribute("food",food.get());
        return "foodAdd";
    }
    //------------------------------------------------------------------------------
    @GetMapping("admin/users")
    public String showUsers(Model model){
        model.addAttribute("users",userRepository.findAll());
        return "users";
    }
    @GetMapping("admin/user/add")
    public String userAdd(Model model){
        model.addAttribute("user", new User());
        return "addUser";
    }
    @PostMapping("admin/user/add")
    public String userPost(@ModelAttribute("user") User user){
        userRepository.save(user);
        return "redirect:/admin/users";
    }
    @GetMapping("/admin/user/delete/{id}")
    public String deleteUser(@PathVariable("id") int id){
        userRepository.deleteById(id);
        return "redirect:/admin/users";
    }
    @GetMapping("/admin/user/update/{id}")
    public String updateUser(@PathVariable int id, Model model){
        model.addAttribute("user",userRepository.findById(id).get());
        return "addUser";
    }
    //-----------------------------------------------------------------------------
    @GetMapping("/admin/orders")
    public String showOrders(Model model){
        model.addAttribute("orders",orderRepository.findAll());

        return "orders";
    }

    @GetMapping("/admin/order/delete/{id}")
    public String deleteOrder(@PathVariable int id){
        orderRepository.deleteById(id);
        return "redirect:/admin/orders";
    }


}

