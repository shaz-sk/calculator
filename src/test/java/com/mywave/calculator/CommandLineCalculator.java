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
            System.out.println("Enter Input as shown, Operand <space> Operation <space> Operand where operation is one of ('+' , '-' , '*' , '/'), or type exit to exit");
            System.out.println("Example: 1 + 1");

            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {

                String input = scanner.nextLine();
                if(input.trim().isEmpty()) continue;
                if("exit".equals(input)){
                    exit = true;
                    break;
                }
                System.out.println("Received input " + input);
                Double result = service.calculate(input);

                if (result == null) {
                    System.out.println("*** Unable to calculate" + " ***");
                } else {
                    System.out.println("*** Response is " + service.calculate(input) + " ***");
                }
                System.out.println();
                break;
            }
        }

    }
}
