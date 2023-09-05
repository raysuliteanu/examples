package misc;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Plants {

    // Complete the poisonousPlants function below.
    static int poisonousPlants(int[] p) {
        List<Integer> list = new LinkedList<>();
        for (int i : p) {
            list.add(i);
        }

        int days = 0;

        while (!done(list)) {
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i) < list.get(i + 1)) {
                    list.remove(i + 1);
                }
            }
            ++days;
        }

        return days;
    }

    private static boolean done(final List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i + 1) > list.get(i)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        int[] input = {6, 5, 8, 4, 7, 10, 9};
//        int[] input = new int[1000];
//        int j = 10;
//        for (int i = 0; i < input.length; i++) {
//            input[i] = j--;
//            if (j == 0) {
//                j = 10;
//            }
//        }
        System.out.println(poisonousPlants(input));
    }
}
