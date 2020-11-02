package com.example.demo.response;

public class Error {
    public String errorMessage;
    public String errorField;

    public Error(String errorMessage, String errorField) {
        this.errorMessage = errorMessage;
        this.errorField = errorField;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setErrorField(String errorField) {
        this.errorField = errorField;
    }
}
