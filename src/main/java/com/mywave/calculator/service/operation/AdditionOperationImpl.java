package com.mywave.calculator.service.operation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdditionOperationImpl implements Operation {
    @Override
    public Double operate(Double a, Double b) {
        log.info("Adding operands {}, {}", a, b);
        return a + b;
    }
}
