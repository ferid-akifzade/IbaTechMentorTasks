package libs;

public class Result {
    private String firstNumber;
    private String secondNumber;
    private String operation;
    private String result;

    public String getFirstNumber() {
        return firstNumber;
    }

    public String getSecondNumber() {
        return secondNumber;
    }
    public String getOperation() {
        return operation;
    }
    public String getResult() {
        return result;
    }

    public Result(String firstNumber, String secondNumber, String operation, String result) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.operation = operation;
        this.result = result;
    }
}
