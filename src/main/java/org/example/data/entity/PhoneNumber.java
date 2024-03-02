package org.example.data.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(schema = "bank", name = "phone_number")
@Data
@NoArgsConstructor
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "number_id")
    private String phoneId;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    private String number;

    public PhoneNumber(User user, String number) {
        this.user = user;
        this.number = number;
    }
}
