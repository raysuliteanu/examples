package misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindDupes {

    static int[] findDuplicates(int[] arr) {
        List<Integer> result = new ArrayList<>(arr.length);

        Arrays.sort(arr);

        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] == arr[i]) {
                result.add(arr[i]);
                while (i < arr.length && arr[i - 1] == arr[i]) {
                    ++i;
                }
            }
        }

        return result.stream().mapToInt(value -> value).toArray();
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findDuplicates(new int[]{1, 2, 3, 4, 5})));
        System.out.println(Arrays.toString(findDuplicates(new int[]{2, 3, 1, 2, 3})));
    }
}
