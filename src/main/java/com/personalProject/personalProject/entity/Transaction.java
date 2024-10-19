package com.personalProject.personalProject.entity;

import com.personalProject.personalProject.dto.Categories;
import com.personalProject.personalProject.dto.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "transactions")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private TransactionType transactionType; // update to enum (deposit, withdrawal, transfer)
    private BigDecimal amount;
    private Date transactionDate;
    private String description;
    private String transactionStatus;

    @Embedded
    private Categories categories;

    @OneToOne(mappedBy = "transaction")
    private Fees fees;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
