package com.mywave.calculator.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class InputValidatorTest {
    @InjectMocks
    @Spy
    private InputValidator inputValidator;

    private BindException errors;

    @BeforeEach
    void setUp() {
        final boolean result = inputValidator.supports(String.class);
        assertTrue(result);
    }

    @ParameterizedTest(name = "should return calculated result for input \"{0}\"")
    @ValueSource(strings = {"1'+'1", "2.9'*'3.0005", "1990909'/'2", "0.009'-'0.008"})
    public void validateValidInput(String input) {
        errors = new BindException(input, "String");
        inputValidator.validate(input, errors);
        assertFalse(errors.hasErrors());
    }

    @Test
    public void validateInvalidInputLength() {
        String input = "1'+";
        errors = new BindException(input, "String");
        inputValidator.validate(input, errors);
        assertTrue(errors.hasErrors());
        assertEquals("Input incomplete", errors.getBindingResult().getAllErrors().get(0).getCode());
    }

    @Test
    public void validateInvalidInputOperand1() {
        String input = "r'+'1";
        errors = new BindException(input, "String");
        inputValidator.validate(input, errors);
        assertTrue(errors.hasErrors());
        assertEquals("Invalid operand", errors.getBindingResult().getAllErrors().get(0).getCode());
    }

    @Test
    public void validateInvalidInputOperand2() {
        String input = "1'+'r";
        errors = new BindException(input, "String");
        inputValidator.validate(input, errors);
        assertTrue(errors.hasErrors());
        assertEquals("Invalid operand", errors.getBindingResult().getAllErrors().get(0).getCode());
    }

    @Test
    public void validateInvalidInputOperation() {
        String input = "4'&'1";
        errors = new BindException(input, "String");
        inputValidator.validate(input, errors);
        assertTrue(errors.hasErrors());
        assertEquals("Invalid operation", errors.getBindingResult().getAllErrors().get(0).getCode());
    }

    @Test
    public void validateInvalidDivisionOperand() {
        String input = "1'/'0";
        errors = new BindException(input, "String");
        inputValidator.validate(input, errors);
        assertTrue(errors.hasErrors());
        assertEquals("Invalid input", errors.getBindingResult().getAllErrors().get(0).getCode());
    }

}