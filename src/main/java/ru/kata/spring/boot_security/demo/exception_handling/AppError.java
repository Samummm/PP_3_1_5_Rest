package ru.kata.spring.boot_security.demo.exception_handling;

public class AppError {
       private String message;



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AppError() {
    }

    public AppError(String message) {
        this.message = message;
    }
}