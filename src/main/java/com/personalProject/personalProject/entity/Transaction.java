package com.personalProject.personalProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.personalProject.personalProject.dto.Categories;
import com.personalProject.personalProject.dto.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "transactions")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType; // update to enum (deposit, withdrawal, transfer)
    private BigDecimal amount;
    @CreationTimestamp
    private Date transactionDate;
    private String description;
    private String transactionStatus;
    @Embedded
    private Categories categories;

//    @OneToOne(mappedBy = "transaction", cascade = CascadeType.ALL)
//    private Fees fees;

//   @ManyToMany
//    private List<Fees> fees; // OR


//    @ManyToMany
//    @JoinTable(
//            name = "transaction_fees",
//            joinColumns = @JoinColumn(name = "tran_id"),
//            inverseJoinColumns = @JoinColumn(name = "fees_id")
//    )
//    private List<Fees> fees;


    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

}
