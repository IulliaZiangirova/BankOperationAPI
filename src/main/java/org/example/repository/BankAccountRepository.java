package org.example.repository;

import org.example.data.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {

    BankAccount findByUserId (String id);
}
