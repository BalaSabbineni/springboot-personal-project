package com.personalProject.personalProject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "fixed_deposits")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FixedDeposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal depositAmount;
    private Double interestRate;
    private Date maturityDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
