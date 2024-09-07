package com.xpert.TravellingAgency.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.xpert.TravellingAgency.model.ContactMessage;

public interface ContactMessageRepository extends MongoRepository<ContactMessage, String> {
}
