package com.gourav.foodapp.repositories;

import com.gourav.foodapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    public User findByEmail(String email);
}
