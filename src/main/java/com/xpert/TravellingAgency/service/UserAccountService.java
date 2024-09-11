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

    public UserAccount findByUsername(String username) {
        return userAccountRepository.findByUsername(username);
    }

    public boolean usernameExists(String username) {
        return userAccountRepository.existsByUsername(username);
    }

    public void save(UserAccount user) {
        userAccountRepository.save(user);
    }

    public UserAccount findById(String id) {
        Optional<UserAccount> user = userAccountRepository.findById(id);
        return user.orElse(null);
    }
    public void updateUser(UserAccount user) {
        userAccountRepository.save(user); 
    }
}
