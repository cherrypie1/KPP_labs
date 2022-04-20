package com.example.demo.repository;

public class InputParams {
    private String operation;
    private int value;

    public InputParams(String operation, int value){
        this.operation = operation;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getOperation() {
        return operation;
    }
}
