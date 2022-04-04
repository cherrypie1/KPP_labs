package com.example.demo.entity;

public enum Operations {
    PLUS_ONE("plus_one") {
        public int action(int x) {
            return ++x;
        }
    },
    MINUS_ONE("minus_one") {
        public int action(int x) {
            return --x;
        }
    },
    PLUS_TEN("plus_ten") {
        public int action(int x) {
            return x + 10;
        }
    },
    MINUS_TEN("minus_ten") {
        public int action(int x) {
            return x - 10;
        }
    };

    private final String operationName;

    Operations(String operationName) {
        this.operationName = operationName;
    }

    public abstract int action(int x);

    public String getOperationName() {
        return operationName;
    }
}
