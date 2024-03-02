package org.example.data.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(schema = "bank", name = "user")
@Data
@NoArgsConstructor
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String login;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    private String password;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private BankAccount bankAccount;

    @OneToMany( cascade = CascadeType.ALL, mappedBy = "user")
    private List<Email> emailList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<PhoneNumber> phoneNumberList;


    public User(String id) {
        this.id = id;
    }
}
