package org.example.repository;

import org.example.data.entity.Email;
import org.example.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, String> {
    Email findByEmail(String email);
}
