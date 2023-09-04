package misc;

import java.math.BigInteger;

public class TwoNumberSum {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        BigInteger one = new BigInteger(listNodeToString(l1));
        BigInteger two = new BigInteger(listNodeToString(l2));

        BigInteger sum = one.add(two);

        return stringToListNode(sum.toString());
    }

    static ListNode stringToListNode(final String value) {
        final char[] array = value.toCharArray();
        ListNode head = new ListNode(array[0] - '0');
        for (int i = 1, arrayLength = array.length; i < arrayLength; i++) {
            final char val = array[i];
            ListNode temp = new ListNode(val - '0');
            temp.next = head;
            head = temp;
        }

        return head;
    }

    static String listNodeToString(final ListNode listNode) {
        StringBuilder value = new StringBuilder(String.valueOf(listNode.val));
        ListNode cur = listNode.next;
        while (cur != null) {
            value.insert(0, cur.val);
            cur = cur.next;
        }
        return value.toString();
    }

    static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
}
