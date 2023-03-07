package com.gourav.foodapp.controllers;

import com.gourav.foodapp.dto.CustomOrder;
import com.gourav.foodapp.models.CustomerOrder;
import com.gourav.foodapp.models.Food;
import com.gourav.foodapp.models.User;
import com.gourav.foodapp.repositories.FoodRepository;
import com.gourav.foodapp.repositories.OrderRepository;
import com.gourav.foodapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    FoodRepository foodRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderRepository orderRepository;

    //-----------------------------------------------------------------------------
    //food
    @GetMapping("/fetchfoods")
    public List<Food> fetchFoods(){
        return foodRepository.findAll();
    }
    @GetMapping("/fetchfood/{id}")
    public Food fetchFoodById(@PathVariable int id){
        return foodRepository.findById(id).get();
    }

    //--------------------------------------------------------------------------
    //users
    @GetMapping("/fetchusers")
    public List<User> fetchUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/fetchuser/{id}")
    public User fetchUserById(@PathVariable int id){
        return userRepository.findById(id).get();
    }


    //---------------------------------------------------------------------------------------------
    //orders
    @GetMapping("/fetchorder/{id}")
    public ResponseEntity<CustomOrder> fetchOrder(@PathVariable int id){
        if(orderRepository.findById(id).isPresent()){
            CustomerOrder customerOrder = orderRepository.findById(id).get();
            CustomOrder customOrder = new CustomOrder();
            customOrder.setId(customerOrder.getId());
            customOrder.setUserId(customerOrder.getUser_id());
            customOrder.setFoodId(customerOrder.getFood_id());
            Food food = foodRepository.findById(customerOrder.getFood_id()).get();
            customOrder.setFoodName(food.getName());
            return ResponseEntity.ok().body(customOrder);
        }
        else
            return ResponseEntity.notFound().build();


    }
}
