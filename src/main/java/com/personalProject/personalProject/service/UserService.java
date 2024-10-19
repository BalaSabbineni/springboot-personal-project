package com.personalProject.personalProject.service;

import com.personalProject.personalProject.dto.BankResponse;
import com.personalProject.personalProject.dto.CreditDebitRequest;
import com.personalProject.personalProject.dto.EnquiryRequest;
import com.personalProject.personalProject.dto.UserRequest;
import com.personalProject.personalProject.entity.User;

import java.util.List;

public interface UserService {
    BankResponse createAccount(UserRequest userRequest);

    void updateAccount(UserRequest userRequest, String accountNumber);

    User getUserDetails(String accountNumber);

    List<User> getUsers();

    BankResponse balanceEnquiry(EnquiryRequest request);

    BankResponse creditAccount(CreditDebitRequest creditDebitRequest);
}
