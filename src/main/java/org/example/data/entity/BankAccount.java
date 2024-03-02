package org.example.data.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "bank", name = "account")
@Data
@NoArgsConstructor
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "account_id")
    private String accountId;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;
    private Double amount;

    public BankAccount(User user, Double bank) {
        this.user = user;
        this.amount = bank;
    }
}
