package com.personalProject.personalProject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.personalProject.personalProject.dto.AccountStatus;
import com.personalProject.personalProject.dto.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "accounts")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //    private BigDecimal loanAmount;
    //    private Double interestRate;
    //    private String repaymentPeriod;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
}
