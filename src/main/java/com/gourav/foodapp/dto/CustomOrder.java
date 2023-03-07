package com.gourav.foodapp.dto;

import lombok.Data;

@Data
public class CustomOrder {
    private int id;
    private int userId;
    private int foodId;
    private String foodName;
}
