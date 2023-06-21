package com.mywave.calculator.service;

import com.mywave.calculator.exception.InvalidOperationException;
import com.mywave.calculator.service.operation.AdditionOperationImpl;
import com.mywave.calculator.service.operation.DivisionOperationImpl;
import com.mywave.calculator.service.operation.MultiplicationOperationImpl;
import com.mywave.calculator.service.operation.Operation;
import com.mywave.calculator.service.operation.SubtractionOperationImpl;
import org.springframework.stereotype.Component;

@Component
public class OperationFactory {

    public Operation getOperation(OperationsEnum operationsEnum) throws InvalidOperationException {
        switch (operationsEnum){
            case ADD: return new AdditionOperationImpl();
            case SUBTRACT: return new SubtractionOperationImpl();
            case MULTIPLY: return new MultiplicationOperationImpl();
            case DIVIDE: return new DivisionOperationImpl();
            case INVALID: throw new InvalidOperationException("InvalidOperation");
        }
        return null;
    }

}
