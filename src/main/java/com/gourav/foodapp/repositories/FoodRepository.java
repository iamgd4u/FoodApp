package com.gourav.foodapp.repositories;

import com.gourav.foodapp.models.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food,Integer> {

    @Query(value = "SELECT food_id FROM customer_order WHERE user_id=:id", nativeQuery = true)
    List<Integer> findIdsById(@Param("id") Integer id);
}
