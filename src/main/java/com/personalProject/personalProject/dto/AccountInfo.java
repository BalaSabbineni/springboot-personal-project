package com.personalProject.personalProject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AccountInfo {

    private String accountName;
    private String accountBalance;
    private String accountNumber;
}
