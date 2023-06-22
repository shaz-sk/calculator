package com.mywave.calculator.integration;

import com.mywave.calculator.service.CalculatorService;
import com.mywave.calculator.service.OperationFactory;
import com.mywave.calculator.validator.InputValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {CalculatorService.class, OperationFactory.class, InputValidator.class})
class CalculatorIntegrationTest {

    @Autowired
    private CalculatorService calculatorService;

    @Test
    public void testAddition(){
        String input = "2 + 9999999999";
        Double result = calculatorService.calculate(input);
        Assertions.assertEquals(1.0000000001E10,result);
    }

    @Test
    public void testSubtraction(){
        String input = "999999.8888 - 9999909";
        Double result = calculatorService.calculate(input);
        Assertions.assertEquals(-8999909.1112,result);
    }

    @Test
    public void testMultiplication(){
        String input = "9999.8888 * 909";
        Double result = calculatorService.calculate(input);
        Assertions.assertEquals(9089898.919200001,result);
    }

    @Test
    public void testDiviision() {
        String input = "99990 * 10";
        Double result = calculatorService.calculate(input);
        Assertions.assertEquals(999900.0, result);
    }
}