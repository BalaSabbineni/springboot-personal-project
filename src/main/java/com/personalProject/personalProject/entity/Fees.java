package com.personalProject.personalProject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@Entity
@Table(name = "fees")
@AllArgsConstructor
@NoArgsConstructor
public class Fees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String feeType;
    private double amount;

//    @OneToOne
//    @JoinColumn(name = "transaction_id")
//    private Transaction transaction;

//    @ManyToMany
//    private List<Transaction> transaction; //OR

//    @ManyToMany(mappedBy = "fees")
//    private List<Transaction> transactions;

}
