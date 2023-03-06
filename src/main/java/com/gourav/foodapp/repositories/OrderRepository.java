package com.gourav.foodapp.repositories;

import com.gourav.foodapp.models.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<CustomerOrder,Integer> {
    @Query(value = "SELECT DISTINCT user_id FROM customer_order", nativeQuery = true)
    List<Integer> findIds();

    @Query(value = "SELECT * FROM customer_order WHERE user_id=:id", nativeQuery = true)
    List<CustomerOrder> findMyOrders(@Param("id") int id);
}
