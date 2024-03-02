package org.example.repository;

import org.example.data.entity.Email;
import org.example.data.entity.PhoneNumber;
import org.example.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhoneRepository extends JpaRepository<PhoneNumber, String> {


    Optional<PhoneNumber>  findByNumber(String number);
}
