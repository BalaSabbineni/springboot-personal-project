package com.personalProject.personalProject.dto;

import com.personalProject.personalProject.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {

    private String firstName;
    private String lastName;
    private String otherName;
    private Gender gender;
    private String address;
    private String country;
    private String stateOfOrigin;
    private String email;
    private String phoneNumber;
    private String alternativePhoneNumber;
    private Status status;
    private String data;
    private List<Account> accounts;
}