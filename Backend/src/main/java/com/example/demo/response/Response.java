package com.example.demo.response;

import java.util.ArrayList;

public class Response {
    public boolean isValid = true;
    public Object responseObject;
    public ArrayList<Error> errors;

    public Response() {
        this.errors = new ArrayList<>();
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public void setResponseObject(Object responseObject) {
        this.responseObject = responseObject;
    }

    public void addError(Error error) {
        this.errors.add(error);
    }
}
