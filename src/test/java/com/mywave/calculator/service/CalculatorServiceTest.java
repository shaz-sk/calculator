package com.mywave.calculator.service;

import com.mywave.calculator.exception.InvalidOperationException;
import com.mywave.calculator.service.operation.AdditionOperationImpl;
import com.mywave.calculator.validator.InputValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.Errors;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculatorServiceTest {

    @InjectMocks
    @Spy
    private CalculatorService service;

    @Mock
    OperationFactory operationFactory;

    @Mock
    AdditionOperationImpl additionOperation;

    @Mock
    InputValidator validator;

    @Test
    void calculateValidInput() throws InvalidOperationException {
        String input = "1'+'1";
        when(operationFactory.getOperation(OperationsEnum.ADD)).thenReturn(additionOperation);
        when(additionOperation.operate(anyDouble(), anyDouble())).thenReturn(1.0);
        when(validator.supports(any())).thenReturn(true);
        service.calculate(input);
        verify(validator,times(1)).validate(any(String.class), any(Errors.class));
        verify(operationFactory,times(1)).getOperation(OperationsEnum.ADD);
        verify(additionOperation,times(1)).operate(1.0, 1.0);
    }
}