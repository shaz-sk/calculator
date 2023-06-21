package com.mywave.calculator;

import com.mywave.calculator.service.CalculatorService;
import com.mywave.calculator.service.OperationFactory;
import com.mywave.calculator.validator.InputValidator;

import java.util.Scanner;

public class CommandLineCalculator{

    public static void main (String... args) {

        CalculatorService service = new CalculatorService(new OperationFactory(), new InputValidator());
        boolean exit = false;

        while(!exit) {
            System.out.println("Enter Input in the format, Operand ‘ Operation ‘ Operand where operation is one of ‘+’ , ‘-’ , ‘*’ , ‘/’)");
            System.out.println("Example 1 `+` 1");
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                String input = scanner.next();
                if("exit".equals(input)){
                    exit = true;
                    break;
                }
                System.out.println("Received input " + input);
                System.out.println("Response is  " + service.calculate(input));
                break;
            }
        }

    }
}
