package com.personalProject.personalProject.service.impl;

import com.personalProject.personalProject.dto.*;
import com.personalProject.personalProject.entity.Account;
import com.personalProject.personalProject.entity.User;
import com.personalProject.personalProject.repository.UserRepository;
import com.personalProject.personalProject.service.UserService;
import com.personalProject.personalProject.utils.AccountUtils;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public BankResponse createAccount(UserRequest userRequest) {
        /**
         * Creating an account and saving new user into the DB
         */
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            return BankResponse.builder().responseCode(AccountUtils.ACCOUNT_EXISTS_CODE).responseMessage(AccountUtils.ACCOUNT_EXISTS_MESSAGE).build();
        }

        User newUser = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .otherName(userRequest.getOtherName())
                .gender(userRequest.getGender().name())
                .address(userRequest.getAddress())
                .country(userRequest.getCountry())
                .phoneNumber(userRequest.getPhoneNumber())
                .email(userRequest.getEmail())
                .status(userRequest.getStatus())
                .accountNumber(AccountUtils.generateAccountNumber())
                .accountBalance(BigDecimal.ZERO).status(Status.ACTIVE)
                .data(userRequest.getData()).build();

        List<Account> accounts = userRequest.getAccounts();
        accounts.forEach(a -> a.setUser(newUser));
        newUser.setAccounts(accounts);

        User savedUser = userRepository.save(newUser);
        return BankResponse.builder().responseCode(AccountUtils.ACCOUNT_CREATED_CODE)
                .responseMessage(AccountUtils.ACCOUNT_CREATED_MESSAGE)
                .accountInfo(AccountInfo.builder().accountName(savedUser.getFirstName() + " " + savedUser.getLastName())
                        .accountBalance(String.valueOf(savedUser.getAccountBalance()))
                        .accountNumber(savedUser.getAccountNumber()).build()).build();
    }

    @Override
    public void updateAccount(UserRequest userRequest, String accountNumber) {
        userRepository.findByAccountNumber(accountNumber).ifPresentOrElse((user) -> {
            user.setPhoneNumber(userRequest.getPhoneNumber());
            userRepository.save(user);
        }, () -> {
            throw new RuntimeException("User Not found");
        });
    }

    @Override
    public User getUserDetails(String accountNumber) {
        return userRepository.findByAccountNumber(accountNumber).get();
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public BankResponse balanceEnquiry(EnquiryRequest request) {
        if (!userRepository.existsByAccountNumber(request.getAccountNumber())) {
            return BankResponse.builder().responseMessage("Account doesn't exist").build();
        }
        User user = userRepository.findByAccountNumber(request.getAccountNumber()).get();
        return BankResponse.builder().accountInfo(AccountInfo.builder().accountNumber(user.getAccountNumber()).accountName(user.getFirstName() + " " + user.getLastName()).accountBalance(String.valueOf(user.getAccountBalance())).build()).build();
    }

    @Override
    public BankResponse creditAccount(CreditDebitRequest creditDebitRequest) {
        return null;
    }


    // balance enquiry, name enquiry, credit, debit, transfer


}