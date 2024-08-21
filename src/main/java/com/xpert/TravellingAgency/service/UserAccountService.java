package com.xpert.TravellingAgency.service;

import com.xpert.TravellingAgency.model.UserAccount;
import com.xpert.TravellingAgency.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    // Find a user by username
    public UserAccount findByUsername(String username) {
        return userAccountRepository.findByUsername(username);
    }

    // Check if a username already exists
    public boolean usernameExists(String username) {
        return userAccountRepository.existsByUsername(username);
    }

    // Save a new user
    public void save(UserAccount user) {
        userAccountRepository.save(user);
    }

    // Find a user by ID
    public UserAccount findById(String id) {
        Optional<UserAccount> user = userAccountRepository.findById(id);
        return user.orElse(null);
    }
}
