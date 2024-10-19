package com.personalProject.personalProject.utils;

import java.time.Year;

public class AccountUtils {

    public static String ACCOUNT_EXISTS_CODE = "001";
    public static String ACCOUNT_EXISTS_MESSAGE = "This user already have an account";
    public static String ACCOUNT_CREATED_CODE = "002";
    public static String ACCOUNT_CREATED_MESSAGE = "Account created successfully";


    /**
     * Create an account number starts with "Current Year + rando, six digits"
     */
    public static String generateAccountNumber() {
        Year year = Year.now();
        int min = 100000;
        int max = 999999;

        int randomNumber = (int) Math.floor(Math.random() * (max - min + 1) + min);

        StringBuilder accountNumber = new StringBuilder();
        return accountNumber.append(String.valueOf(year)).append(String.valueOf(randomNumber)).toString();
    }


}
