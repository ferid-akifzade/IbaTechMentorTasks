package main;

public class Calculator {
    private static Double lastResult = null;
    public static boolean lastResultWanted = false;
    private Calculator() {
    }

    public static String add(String x, String y) {
        lastResult = Parser.parse(x) + Parser.parse(y);
        return String.valueOf(lastResult);
    }

    public static String subtract(String x, String y) {
        lastResult = Parser.parse(x) - Parser.parse(y);
        return String.valueOf(lastResult);
    }

    public static String multiply(String x, String y) {
        lastResult = Parser.parse(x) * Parser.parse(y);
        return String.valueOf(lastResult);
    }

    public static String remainder(String x, String y) {
        lastResult = Parser.parse(x) % Parser.parse(y);
        return String.valueOf(lastResult);
    }

    public static String divide(String x, String y){
        if (y.equals("0"))
            return "You are not allowed to divide by zero";

        lastResult = Parser.parse(x) / Parser.parse(y);
        return String.valueOf(lastResult);
    }

    public static String calculate(String x, String y, String operation) {
        if (x.isEmpty() || y.isEmpty() || operation.isEmpty())
            return "Please fill all fields";

        switch (operation) {
            case "+":
                return Calculator.add(x, y);
            case "-":
                return Calculator.subtract(x, y);
            case "*":
                return Calculator.multiply(x, y);
            case "/":
                return Calculator.divide(x, y);
            case "%":
                return Calculator.remainder(x, y);
            default:
                return "Unsupported operation";
        }
    }

    public static String getLastResult() {
        if (lastResult == null)
            return "There is no calculation in history";
        else
            return lastResult.toString();
    }
}
