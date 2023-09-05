package misc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        List<Integer> merged = new ArrayList<>();
        for (ListNode list : lists) {
            while (list != null) {
                merged.add(list.val);
                list = list.next;
            }
        }

        if (merged.isEmpty()) {
            return null;
        }

        Collections.sort(merged);

        ListNode list = new ListNode();
        ListNode cursor = list;
        for (int i = 0, mergedSize = merged.size(); i < mergedSize - 1; i++) {
            cursor.val = merged.get(i);
            cursor.next = new ListNode();
            cursor = cursor.next;
        }

        cursor.val = merged.get(merged.size() - 1);

        return list;
    }

    public static void main(String[] args) {
        ListNode output = new SortLists().mergeKLists(toListNode(new int[][]{{1, 4, 5}, {1, 3, 4}, {2, 6}}));
        while (output != null) {
            System.out.print(output.val);
            output = output.next;
        }
        System.out.println();
    }

    static ListNode[] toListNode(final int[][] values) {
        final ListNode[] lists = new ListNode[values.length];
        for (int i = 0, valueLength = values.length; i < valueLength; i++) {
            final int[] ints = values[i];
            lists[i] = new ListNode();
            ListNode current = lists[i];
            for (int j = 0; j < ints.length - 1; j++) {
                current.val = ints[j];
                current.next = new ListNode();
                current = current.next;
            }
            current.val = ints[ints.length - 1];
        }

        return lists;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
