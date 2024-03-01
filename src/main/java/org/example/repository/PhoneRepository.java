package org.example.repository;

import org.example.data.entity.Email;
import org.example.data.entity.PhoneNumber;
import org.example.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<PhoneNumber, String> {
    Email findByNumber(String number);
}
