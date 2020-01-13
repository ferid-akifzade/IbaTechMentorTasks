import main.Calculator;
import org.junit.jupiter.api.Test;
import  org.junit.jupiter.api.Assertions;

public class MainAppTest {

    @Test
    public void addTest()
    {
        String x = "5";
        String y = "4";
        Assertions.assertEquals(9, Calculator.add(x,y));
    }
    @Test
    public void subsTest()
    {
        String x = "5";
        String y = "4";
        Assertions.assertEquals(1, Calculator.subtract(x,y));
    }
    @Test
    public void multTest()
    {
        String x = "5";
        String y = "4";
        Assertions.assertEquals(20, Calculator.multiply(x,y));
    }
    @Test
    public void divTest()
    {
        String x = "5";
        String y = "4";
        Assertions.assertEquals(1.25, Calculator.divide(x,y));
    }
    @Test
    public void divByZeroTest()
    {
        String x = "5";
        String y = "0";

    }

    @Test
    public void firstIsMTest()
    {

    }
    @Test
    public void secondIsMTest()
    {

    }
    @Test
    public void bothIsMTest()
    {

    }


}
