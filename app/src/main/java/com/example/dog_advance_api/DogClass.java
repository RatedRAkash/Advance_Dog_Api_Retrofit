package com.example.dog_advance_api;

public class DogClass {
    private String message;
    private String status;

    public DogClass(String message, String status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}
