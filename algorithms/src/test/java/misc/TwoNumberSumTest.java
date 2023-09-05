package misc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TwoNumberSumTest {
    TwoNumberSum twoNumberSum = new TwoNumberSum();

    @Test
    void sum() {
        TwoNumberSum.ListNode l1 = TwoNumberSum.stringToListNode("342");
        TwoNumberSum.ListNode l2 = TwoNumberSum.stringToListNode("465");
        final TwoNumberSum.ListNode result = twoNumberSum.addTwoNumbers(l1, l2);
        assertEquals("807", TwoNumberSum.listNodeToString(result));
    }

    @Test
    void sumLarge() {
        TwoNumberSum.ListNode l1 = TwoNumberSum.stringToListNode("9999999");
        TwoNumberSum.ListNode l2 = TwoNumberSum.stringToListNode("9999");
        final TwoNumberSum.ListNode result = twoNumberSum.addTwoNumbers(l1, l2);
        assertEquals("10009998", TwoNumberSum.listNodeToString(result));
    }

    @Test
    void sumSmall() {
        TwoNumberSum.ListNode l1 = TwoNumberSum.stringToListNode("0");
        TwoNumberSum.ListNode l2 = TwoNumberSum.stringToListNode("1");
        final TwoNumberSum.ListNode result = twoNumberSum.addTwoNumbers(l1, l2);
        assertEquals("1", TwoNumberSum.listNodeToString(result));
    }

}