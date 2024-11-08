package com.personalProject.personalProject.controller;

import com.personalProject.personalProject.dto.BankResponse;
import com.personalProject.personalProject.dto.EnquiryRequest;
import com.personalProject.personalProject.dto.UserRequest;
import com.personalProject.personalProject.entity.User;
import com.personalProject.personalProject.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/user", produces = {MediaType.APPLICATION_JSON_VALUE})
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //@Scheduled(fixedRate = 5000L)
    public void job3() {
        logger.info("Scheduler.. job 3 {}", Instant.now());
    }


    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public BankResponse createUser(@RequestBody UserRequest userRequest) {
        logger.info("user is created {}", userRequest.getFirstName());
        return userService.createUser(userRequest);
    }


    @PutMapping("updateAccount/{accountNumber}")
    public void updateUser(@RequestBody UserRequest userRequest, @PathVariable("accountNumber") String accountNumber) {
        userService.updateUser(userRequest, accountNumber);
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
