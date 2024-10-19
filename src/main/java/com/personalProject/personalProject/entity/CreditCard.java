package com.personalProject.personalProject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "credit_cards")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long creditCardId;
    private String cardNumber;
    private Date expirationDate;
    private int cvv;
    private int creditLimit;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
