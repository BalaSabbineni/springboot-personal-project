package com.personalProject.personalProject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bank_branch")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BankBranch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String branchName;
    private String address;
    private String city;
    private String state;
    private String country;
}
