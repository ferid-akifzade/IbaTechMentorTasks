package main;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        System.out.println("Welcome to calculator app.");
        while (true) {
            Scanner userInput = new Scanner(System.in);
            System.out.println("Please write first number. If you want to see last result type [-M]");
            String firstInput = userInput.nextLine();
            if (Parser.isLastResultWanted(firstInput)) {
                System.out.println(String.format("Last Result: %s",Calculator.getLastResult()));
                continue;
            }
            System.out.println("Please write operation. The possible operations are: +, -, /, *, %(remainder)");
            String operation = userInput.nextLine();
            if (Parser.isLastResultWanted(operation)) {
                System.out.println(String.format("Last Result: %s",Calculator.getLastResult()));
                continue;
            }
            System.out.println("Please write second number. If you want to see last result type [-M]");
            String secondInput = userInput.nextLine();
            if (Parser.isLastResultWanted(secondInput)) {
                System.out.println(String.format("Last Result: %s",Calculator.getLastResult()));
                continue;
            }

            System.out.println("Result:");
            System.out.println(Calculator.calculate(firstInput, secondInput, operation));

        }
    }
}
