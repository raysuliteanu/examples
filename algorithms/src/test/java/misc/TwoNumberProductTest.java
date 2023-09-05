package misc;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TwoNumberProductTest {
    TwoNumberProduct twoNumberProduct = new TwoNumberProduct();

    @Test
    void small() {
        assertEquals("30", twoNumberProduct.multiply("5", "6"));
    }

    @Test
    void large() {
        char[] one = new char[200];
        Arrays.fill(one, '9');
        char[] two = new char[200];
        Arrays.fill(two, '9');
        final String num1 = new String(one);
        final String num2 = new String(two);
        assertEquals(new BigInteger(num1).multiply(new BigInteger(num2)).toString(), twoNumberProduct.multiply(num1, num2));
    }
}