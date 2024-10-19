package com.personalProject.personalProject.dto;

import com.personalProject.personalProject.entity.Transaction;
import lombok.Data;

@Data
public class WebClientResponse {

    private String accountId;
    private Transaction transactions;
    private double balance;
    private double availableBalance;
    private String currency;

}
