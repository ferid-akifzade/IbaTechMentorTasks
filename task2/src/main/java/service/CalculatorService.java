package service;

import dao.CalculationDAO;
import libs.Result;

public class CalculatorService {
    private Double lastResult = null;
    private CalculationDAO collections;

    public CalculatorService() {
        collections  = new CalculationDAO();
    }

    public String add(String x, String y) {
        lastResult = Parser.parse(x) + Parser.parse(y);
        return String.valueOf(lastResult);
    }

    public String subtract(String x, String y) {
        lastResult = Parser.parse(x) - Parser.parse(y);
        return String.valueOf(lastResult);
    }

    public String multiply(String x, String y) {
        lastResult = Parser.parse(x) * Parser.parse(y);
        return String.valueOf(lastResult);
    }

    public String remainder(String x, String y) {
        lastResult = Parser.parse(x) % Parser.parse(y);
        return String.valueOf(lastResult);
    }

    public String divide(String x, String y) {
        lastResult = Parser.parse(x) / Parser.parse(y);
        return String.valueOf(lastResult);
    }
    public String save(String x, String y, String  operation, String result) {
        collections.add(new Result(x,y,operation,result));
        return result;
    }
    public String calculate(String x, String y, String operation) {
        if (x.isEmpty() || y.isEmpty() || operation.isEmpty())
            return "Please fill all fields";
        if (!Parser.isNumeric(x, y))
            return "Only numbers allowed";
        if (y.equals("0") && operation.equals("/"))
            return "You are not allowed to divide by zero";

        switch (operation) {
            case "+":
                return save(x,y,operation,add(x, y));
            case "-":
                return save(x,y,operation,subtract(x, y));
            case "*":
                return save(x,y,operation,multiply(x,y));
            case "/":
                return save(x,y,operation,divide(x,y));
            case "%":
                return save(x,y,operation,remainder(x,y));
        }
        return save(x,y,operation,add(x, y));
    }

}
