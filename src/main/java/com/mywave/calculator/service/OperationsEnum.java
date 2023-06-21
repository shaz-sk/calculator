package com.mywave.calculator.service;

import java.util.Arrays;

public enum OperationsEnum {
    ADD ("+"), SUBTRACT("-"),MULTIPLY ("*"), DIVIDE("/"), INVALID(".");

    private final String operation;
    OperationsEnum(String operation) {
        this.operation = operation;
    }

    public static final OperationsEnum getByValue(String value){
        return Arrays.stream(OperationsEnum.values()).filter(operationsEnum -> operationsEnum.operation.equals(value)).findFirst().orElse(INVALID);
    }

    public String getValue() {
        return this.operation;
    }
}
