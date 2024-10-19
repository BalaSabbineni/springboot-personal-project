package com.personalProject.personalProject.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "fees")
public class Fees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String feeType;
    private double amount;

    @OneToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;
}
