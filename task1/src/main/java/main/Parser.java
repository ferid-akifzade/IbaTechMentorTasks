package main;

public class Parser {
    private Parser() {
    }

    public static boolean isNumeric(String... origin) {
        for (String oneOrigin : origin) {
            try {
                double i = Double.parseDouble(oneOrigin);
                return true;
            } catch (NumberFormatException ex) {
                return false;
            }
        }
        return false;
    }

    public static boolean isLastResultWanted(String origin)
    {
        return origin.equals("-M") || origin.equals("-m");
    }

    public static double parse(String origin) {
        return Double.parseDouble(origin);
    }
}
