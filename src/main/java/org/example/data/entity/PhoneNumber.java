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
    @Column(name = "email_id")
    private String phoneId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String number;

    public PhoneNumber(String number) {
        this.number = number;
    }
}
