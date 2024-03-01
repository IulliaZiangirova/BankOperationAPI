package org.example.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "bank", name = "account")
@Data
@NoArgsConstructor
public class BankAccount {

    @Id
    @Column(name = "account_id")
    private String accountId;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;
    private Double bank;

    public BankAccount(Double bank) {
        this.bank = bank;
    }
}
