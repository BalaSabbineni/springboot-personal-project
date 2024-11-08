package com.personalProject.personalProject.controller;

import com.personalProject.personalProject.dto.AccountStatus;
import com.personalProject.personalProject.dto.Status;
import com.personalProject.personalProject.entity.Account;
import com.personalProject.personalProject.repository.AccountRepository;
import com.personalProject.personalProject.repository.UserRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/account")
public class AccountController {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountController(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/{userId}")
    @Cacheable(value = "accounts", key = "#userId")
    public List<Account> getAccounts(@PathVariable long userId) {
        var accounts = accountRepository.findAccountsByUserId(userId);
        return accounts.orElseThrow();
    }

    @PostMapping("/{userId}")
    @CacheEvict(value = "account", key = "#userId")
    public Account create(@PathVariable long userId, @RequestBody Account account) {
        var user = userRepository.findById(userId).orElseThrow();
        account.setUser(user);
        return accountRepository.save(account);
    }
}
