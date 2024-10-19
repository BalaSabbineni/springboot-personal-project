package com.personalProject.personalProject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class BankResponse {

    private AccountInfo accountInfo;
    private String responseCode;
    private String responseMessage;
}
