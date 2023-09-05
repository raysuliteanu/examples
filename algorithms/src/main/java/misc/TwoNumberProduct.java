package misc;

import java.math.BigInteger;

public class TwoNumberProduct {
    public String multiply(String num1, String num2) {
        BigInteger one = new BigInteger(num1);
        BigInteger two = new BigInteger(num2);

        return one.multiply(two).toString();
    }
}
