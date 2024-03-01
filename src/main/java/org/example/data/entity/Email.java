package org.example.data.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(schema = "bank", name = "email")
@Data
public class Email {
    @Id
    @Column(name = "email_id")
    private String idEmail;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String email;

    public Email(String email) {
        this.email = email;
    }
}
