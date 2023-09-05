package misc;

import org.junit.jupiter.api.Test;

import static misc.Fibonacci.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FibonacciTest {
    @Test
    void fibonacciRecursive() {
        assertEquals(0, fib(0));
        assertEquals(1, fib(1));
        assertEquals(1, fib(2));
        assertEquals(2, fib(3));
        assertEquals(3, fib(4));
        assertEquals(5, fib(5));
        assertEquals(8, fib(6));
        assertEquals(13, fib(7));
        assertEquals(21, fib(8));
        assertEquals(34, fib(9));
    }

    @Test
    void fibonacciBottomUp() {
        assertEquals(0, fibBU(0));
        assertEquals(1, fibBU(1));
        assertEquals(1, fibBU(2));
        assertEquals(2, fibBU(3));
        assertEquals(3, fibBU(4));
        assertEquals(5, fibBU(5));
        assertEquals(8, fibBU(6));
        assertEquals(13, fibBU(7));
        assertEquals(21, fibBU(8));
        assertEquals(34, fibBU(9));
    }

    @Test
    void fibonacciMemo() {
        assertEquals(0, fibMemo(0));
        assertEquals(1, fibMemo(1));
        assertEquals(1, fibMemo(2));
        assertEquals(2, fibMemo(3));
        assertEquals(3, fibMemo(4));
        assertEquals(5, fibMemo(5));
        assertEquals(8, fibMemo(6));
        assertEquals(13, fibMemo(7));
        assertEquals(21, fibMemo(8));
        assertEquals(34, fibMemo(9));
    }
}