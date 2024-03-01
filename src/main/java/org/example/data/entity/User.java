package org.example.data.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(schema = "bank", name = "user")
@Data
public class User
{
    @Id
    private String id;
    private String login;
    private String fcs;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    private String password;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private BankAccount bankAccount;
    @OneToMany(mappedBy = "user")
    private List<Email> emailList;
    @OneToMany(mappedBy = "user")
    private List<PhoneNumber> phoneNumberList;



}
