package com.gourav.foodapp.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int user_id;
    private int food_id;

    public CustomerOrder() {
    }

    public CustomerOrder(int id, int user_id, int food_id) {
        this.id = id;
        this.user_id = user_id;
        this.food_id = food_id;
    }
}
