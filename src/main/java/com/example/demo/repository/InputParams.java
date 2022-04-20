package com.example.demo.repository;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputParams that = (InputParams) o;
        return value == that.value && operation.equals(that.operation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operation, value);
    }
}
