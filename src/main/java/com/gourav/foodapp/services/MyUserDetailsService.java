package com.gourav.foodapp.services;

import com.gourav.foodapp.models.MyUserDetails;
import com.gourav.foodapp.models.User;
import com.gourav.foodapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> byUsername = userRepository.findByUsername(username);
        if(byUsername.isPresent())
            return new MyUserDetails(byUsername.get());
        else
            return new MyUserDetails(new User());
    }
}
