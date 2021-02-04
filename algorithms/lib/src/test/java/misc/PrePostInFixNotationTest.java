package misc;

import java.util.EmptyStackException;

import org.junit.jupiter.api.Test;

import static misc.PrePostInFixNotation.evalPrefixExpression;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PrePostInFixNotationTest {
    @Test
    void validEvalPrefixExpression() {
        String expression = "- 5 * 6 7";
        assertEquals(5 - (6 * 7), evalPrefixExpression(expression), expression);

        expression = "* - 5 6 7";
        assertEquals((5 - 6) * 7, evalPrefixExpression(expression), expression);

        expression = "+ - + - + 1 2 3 4 5 6";
        assertEquals(1 + 2 - 3 + 4 - 5 + 6, evalPrefixExpression(expression), expression);

        expression = "% 6 5";
        assertEquals(6 % 5, evalPrefixExpression(expression), expression);

        expression = "* 6 / 6 5";
        assertEquals(6 / 5 * 6, evalPrefixExpression(expression), expression);
    }

    @Test
    void invalidEvalPrefixExpression() {
        // not a parsable integer
        assertThrows(IllegalArgumentException.class, () -> evalPrefixExpression("- 5 * 6 a"));

        // must start with an operand
        assertThrows(IllegalArgumentException.class, () -> evalPrefixExpression("5 * 6 a"));

        // not enough operands
        assertThrows(EmptyStackException.class, () -> evalPrefixExpression("- 5 * 6"));
    }

}