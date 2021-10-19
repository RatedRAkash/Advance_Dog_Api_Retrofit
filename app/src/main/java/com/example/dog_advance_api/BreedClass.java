package com.example.dog_advance_api;

import java.util.ArrayList;
import java.util.HashMap;

public class BreedClass {

    //@SerializedName("message") //Jodi JSON theke alada name dite chai
    private HashMap<String,ArrayList<String>> message;
    private String status;

    public BreedClass(HashMap<String,ArrayList<String>> message, String status) {
        this.message = message;
        this.status = status;
    }

    public HashMap<String,ArrayList<String>> getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}
