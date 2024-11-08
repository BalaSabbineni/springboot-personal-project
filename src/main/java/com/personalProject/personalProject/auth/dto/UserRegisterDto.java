package com.personalProject.personalProject.auth.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegisterDto {
    private String userName;
    private String passWord;
}
