package misc;

import java.util.*;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) {
            return Collections.emptyList();
        }

        Arrays.sort(nums);

        List<List<Integer>> results = new LinkedList<>();
        int prevValue = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                break;
            }

            if (nums[i] == prevValue) {
                continue;
            }

            prevValue = nums[i];

            twoSum(nums, i, results);
        }

        return results;

    }

    public void twoSum(int[] numbers, int i, List<List<Integer>> results) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int j = i + 1; j < numbers.length; j++) {
            if (map.containsKey(-numbers[i] - numbers[j])) {
                int k = map.get(-numbers[i] - numbers[j]);
                results.add(Arrays.asList(numbers[i], numbers[k], numbers[j]));

                while (j < numbers.length - 1 && numbers[j] == numbers[j + 1]) {
                    ++j;
                }
            }

            map.put(numbers[j], j);
        }
    }

    public static void main(String[] args) {
        System.out.println(new ThreeSum().threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
        System.out.println(new ThreeSum().threeSum(new int[]{0, 0, 0, 0}));
    }
}
