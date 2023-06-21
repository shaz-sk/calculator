package com.mywave.calculator.model;

import com.mywave.calculator.service.OperationsEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class InputDto {
    private Double operand1;
    private Double operand2;
    private OperationsEnum operation;
}
