package com.mywave.calculator.validator;

import com.mywave.calculator.service.OperationsEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Slf4j
@Component
public class InputValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(String.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        String input = (String) target;
        String[] inputValues = input.split("'");
        if (inputValues.length != 3) {
            errors.reject("Input incomplete");
            return;
        }
        Pattern pattern = Pattern.compile("[0-9]+[.]?[0-9]*");
        if (!pattern.matcher(inputValues[0]).find() || !pattern.matcher(inputValues[2]).find()){
            errors.reject("Invalid operand");
            return;
        }

        OperationsEnum operationsEnum = OperationsEnum.getByValue(inputValues[1]);
        if (operationsEnum == OperationsEnum.INVALID){
            errors.reject("Invalid operation");
            return;
        }

        if (operationsEnum == OperationsEnum.DIVIDE && Double.parseDouble(inputValues[2]) == 0){
            errors.reject("Invalid input");
        }

    }
}
