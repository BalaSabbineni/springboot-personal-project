package com.personalProject.personalProject.controller;

import com.personalProject.personalProject.dto.BankResponse;
import com.personalProject.personalProject.dto.EnquiryRequest;
import com.personalProject.personalProject.dto.UserRequest;
import com.personalProject.personalProject.entity.User;
import com.personalProject.personalProject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public BankResponse createAccount(@RequestBody UserRequest userRequest) {
        logger.info("user is created {}",  userRequest.getFirstName());
        return userService.createAccount(userRequest);
    }

    @PutMapping("updateAccount/{accountNumber}")
    public void updateAccount(@RequestBody UserRequest userRequest, @PathVariable("accountNumber") String accountNumber) {
        userService.updateAccount(userRequest, accountNumber);
    }

    @GetMapping("/{accountNumber}")
    public User getUserDetails(@PathVariable String accountNumber) {
        return userService.getUserDetails(accountNumber);
    }

    @GetMapping()
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/balanceEnquiry")
    public BankResponse getName(@RequestBody EnquiryRequest request) {
        return userService.balanceEnquiry(request);
    }
}
