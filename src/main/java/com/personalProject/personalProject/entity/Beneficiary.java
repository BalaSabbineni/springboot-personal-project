package com.personalProject.personalProject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "beneficiaries")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Beneficiary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long beneficiaryId;
    private String beneficiaryName;
    private String accountNumber;
    private String bankName;

    /**
     * This @JsonBackReference annotation is for fixing  "Infinite Recursion" or "Circular Reference" problem.
     * It occurs when two or more entities reference each other, causing the JSON serialization process to enter an infinite loop.
     */
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
