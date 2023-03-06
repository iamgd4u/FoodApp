package com.gourav.foodapp.globaldata;

import com.gourav.foodapp.models.Food;
import com.gourav.foodapp.models.User;

import java.util.ArrayList;
import java.util.List;

public class GlobalData {

    public static List<Food> cart = new ArrayList<>();

    public static double total(){
        double sum = 0;
        for(Food f : cart){
            sum += f.getPrice();
        }
        return sum;
    }
    public static User currentUser = new User();
    public static Boolean isLoggedIn = false;
}
