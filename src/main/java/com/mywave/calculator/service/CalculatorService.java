package com.mywave.calculator.service;

import com.mywave.calculator.exception.InvalidInputException;
import com.mywave.calculator.exception.InvalidOperationException;
import com.mywave.calculator.model.InputDto;
import com.mywave.calculator.service.operation.Operation;
import com.mywave.calculator.validator.InputValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

@Slf4j
@Service
public class CalculatorService {
    private final OperationFactory operationFactory;
    private final InputValidator inputValidator;

    public CalculatorService(@Autowired OperationFactory operationFactory,
                             @Autowired InputValidator inputValidator){
        this.operationFactory = operationFactory;
        this.inputValidator = inputValidator;
    }

    public Double calculate(String input) {
        log.info("Calculating " + input);
        try {
            InputDto inputDto = validate(input);
            Operation operationImpl = operationFactory.getOperation(inputDto.getOperation());
            return operationImpl.operate(inputDto.getOperand1(), inputDto.getOperand2());
        } catch (InvalidInputException ie){
            if( ie.getErrors().hasErrors()) {
                log.error("Validation error in input: {}", (ie.getErrors().getAllErrors().get(0).getCode()));
            }
        } catch (InvalidOperationException e) {
            log.error("Received Invalid operation");
        }
        return null;
    }

    private InputDto validate(String input){
        DataBinder binder = new DataBinder(input);
        binder.setValidator(inputValidator);
        binder.validate();

        final BindingResult bindingResult = binder.getBindingResult();
        if(bindingResult.hasErrors()){
            throw new InvalidInputException(bindingResult);
        }
        String[] inputValues = input.trim().split("\\s+");
        return new InputDto( Double.parseDouble(inputValues[0]),Double.parseDouble(inputValues[2]),
                OperationsEnum.getByValue(inputValues[1]));

    }
}
