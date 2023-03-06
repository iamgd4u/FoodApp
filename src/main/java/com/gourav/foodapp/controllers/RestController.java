package com.gourav.foodapp.controllers;

import com.gourav.foodapp.models.CustomerOrder;
import com.gourav.foodapp.models.Food;
import com.gourav.foodapp.models.User;
import com.gourav.foodapp.repositories.FoodRepository;
import com.gourav.foodapp.repositories.OrderRepository;
import com.gourav.foodapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    FoodRepository foodRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderRepository orderRepository;
    @GetMapping("/fetchfoods")
    public List<Food> fetchFoods(){
        return foodRepository.findAll();
    }
    @GetMapping("/fetchusers")
    public List<User> fetchUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/fetchuser/{id}")
    public User fetchUserById(@PathVariable int id){
        return userRepository.findById(id).get();
    }
    @GetMapping("/fetchfood/{id}")
    public Food fetchFoodById(@PathVariable int id){
        return foodRepository.findById(id).get();
    }
}
