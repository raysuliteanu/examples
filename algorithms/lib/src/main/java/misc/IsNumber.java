package misc;

import java.util.regex.Pattern;

public class IsNumber {
    private static final String SIGN =
            "[+-]?";
    private static final String DIGITS =
            "\\d+";
    private static final String EXP =
            "[eE]" + SIGN + DIGITS;
    private static final String INTEGER =
            SIGN + DIGITS + "(" + EXP + ")?";
    public static final String PERIOD = "\\.";
    private static final String DECIMAL =
                    "(" + SIGN + DIGITS + PERIOD +")|" +
                    "(" + SIGN + PERIOD + DIGITS + ")(" + EXP + ")?|" +
                    "(" + SIGN + DIGITS + PERIOD + "(" + DIGITS + ")?)(" + EXP + ")?";

    private static final String REGEX = INTEGER + "|" + DECIMAL;

    private static final Pattern pattern = Pattern.compile(REGEX);

    public static boolean isNumber(String s) {
        if (s == null) return false;
        return pattern.matcher(s).matches();

    }

    public static void main(String[] args) {
        System.out.println("These should all be true");
        System.out.println(isNumber("0"));
        System.out.println(isNumber("+0"));
        System.out.println(isNumber("-0"));
        System.out.println(isNumber("-10"));
        System.out.println(isNumber("+10"));
        System.out.println(isNumber("10"));
        System.out.println(isNumber("10e1"));
        System.out.println(isNumber("10e+1"));
        System.out.println(isNumber("10e-1"));
        System.out.println(isNumber("10."));
        System.out.println(isNumber("10.1"));
        System.out.println(isNumber(".10"));
        System.out.println(isNumber(".10e2"));
        System.out.println(isNumber("10.10e2"));
        System.out.println(isNumber("10.10e+2"));
        System.out.println(isNumber("10.10e-2"));
        System.out.println(isNumber("+10."));
        System.out.println(isNumber(".8"));
        System.out.println(isNumber(".08"));
        System.out.println(isNumber(".80"));
        System.out.println(isNumber("+.10"));
        System.out.println(isNumber("-.10"));
        System.out.println(isNumber("+10.10e2"));
        System.out.println(isNumber("+10.10e+2"));
        System.out.println(isNumber("+10.10e-2"));
        System.out.println(isNumber("-10.10e2"));
        System.out.println(isNumber("-10.10e+2"));
        System.out.println(isNumber("-10.10e-2"));
        System.out.println(isNumber("10.e-2"));
        System.out.println(isNumber("10.e+2"));

        System.out.println();

        System.out.println("These should all be false");
        System.out.println(isNumber("e2"));
        System.out.println(isNumber(".e2"));
        System.out.println(isNumber("9.e9.2"));
        System.out.println(isNumber("abc"));
        System.out.println(isNumber("1a"));

        System.out.println(isNumber(".-6"));

    }
}
