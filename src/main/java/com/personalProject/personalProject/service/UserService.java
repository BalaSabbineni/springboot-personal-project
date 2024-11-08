package com.personalProject.personalProject.service;

import com.personalProject.personalProject.dto.BankResponse;
import com.personalProject.personalProject.dto.CreditDebitRequest;
import com.personalProject.personalProject.dto.EnquiryRequest;
import com.personalProject.personalProject.dto.UserRequest;
import com.personalProject.personalProject.entity.User;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface UserService {
    BankResponse createUser(UserRequest userRequest);

    void updateUser(UserRequest userRequest, String accountNumber);

    User getUserDetails(String accountNumber);

    List<User> getUsers();

    BankResponse balanceEnquiry(EnquiryRequest request);

    BankResponse creditAccount(CreditDebitRequest creditDebitRequest);
}
