package com.personalProject.personalProject.controller;


import com.personalProject.personalProject.entity.Fees;
import com.personalProject.personalProject.entity.Transaction;
import com.personalProject.personalProject.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/transaction")
public class TransactionController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TransactionRepository transactionRepository;

    public TransactionController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public void create(@RequestBody Transaction transaction) {
//        Fees fees = transaction.getFees();
//        fees.setTransaction(transaction);
//        transactionRepository.save(transaction);
//    }

}
