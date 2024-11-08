package com.personalProject.personalProject.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.personalProject.personalProject.dto.Gender;
import com.personalProject.personalProject.dto.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String address;
    @Column(nullable = false)
    private String accountNumber;
    @Column(nullable = false)
    private String email;
    // @Column(nullable = false)
    private String phoneNumber;
    private BigDecimal accountBalance;
    @Enumerated(EnumType.STRING)
    private Status status;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime modifyAt;
    @Column(columnDefinition = "JSON")
    private String data;

    /**
     * This @JsonManagedReference annotation is for fixing  "Infinite Recursion" or "Circular Reference" problem.
     * It occurs when two or more entities reference each other, causing the JSON serialization process to enter an infinite loop.
     */
    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Account> accounts;
    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Beneficiary> beneficiaries;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CreditCard> creditCards;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FixedDeposit> fixedDeposits;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;

//    @OneToMany // if we add only this without reference on FD table, it will create new join table with user_id and fd_id
//    private List<FixedDeposit> fixedDeposits;
}
