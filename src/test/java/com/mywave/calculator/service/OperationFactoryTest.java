package com.mywave.calculator.service;

import com.mywave.calculator.exception.InvalidOperationException;
import com.mywave.calculator.service.operation.AdditionOperationImpl;
import com.mywave.calculator.service.operation.DivisionOperationImpl;
import com.mywave.calculator.service.operation.MultiplicationOperationImpl;
import com.mywave.calculator.service.operation.SubtractionOperationImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperationFactoryTest {

    private OperationFactory operationFactory = new OperationFactory();

    @Test
    void getOperationAddition() {
        Assertions.assertInstanceOf(AdditionOperationImpl.class,operationFactory.getOperation(OperationsEnum.ADD));
    }
    @Test
    void getOperationSubtraction() {
        Assertions.assertInstanceOf(SubtractionOperationImpl.class,operationFactory.getOperation(OperationsEnum.SUBTRACT));
    }
    @Test
    void getOperationMultiplication() {
        Assertions.assertInstanceOf(MultiplicationOperationImpl.class,operationFactory.getOperation(OperationsEnum.MULTIPLY));
    }
    @Test
    void getOperationDivision() {
        Assertions.assertInstanceOf(DivisionOperationImpl.class,operationFactory.getOperation(OperationsEnum.DIVIDE));
    }
    @Test
    void getOperationInvalid() {
        assertThrows(
                InvalidOperationException.class,
                () -> operationFactory.getOperation(OperationsEnum.INVALID),
                "Expected getOperation() to throw, but it didn't"
        );
    }
}