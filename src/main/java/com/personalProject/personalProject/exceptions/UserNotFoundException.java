package com.personalProject.personalProject.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("User Not Found");
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
