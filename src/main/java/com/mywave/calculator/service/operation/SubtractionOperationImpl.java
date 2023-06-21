package com.mywave.calculator.service.operation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SubtractionOperationImpl implements Operation {
    @Override
    public Double operate(Double a, Double b) {
        log.info("Subtracting operands {}, {}", a, b);
        return a-b;
    }
}
