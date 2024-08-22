package com.xpert.TravellingAgency.repository;

import com.xpert.TravellingAgency.model.UserAccount;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserAccountRepository extends MongoRepository<UserAccount, String> {
    UserAccount findByUsername(String username);
    boolean existsByUsername(String username);
    Optional<UserAccount> findById(String id); 
}
