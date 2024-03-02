package org.example.data.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "bank", name = "email")
@Data
@NoArgsConstructor
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "email_id")
    private String idEmail;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    private String email;

    public Email(User  user, String email) {
        this.user = user;
        this.email = email;
    }
}
