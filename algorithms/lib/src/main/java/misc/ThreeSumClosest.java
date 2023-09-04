package misc;

import java.util.Arrays;

public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        int minDistance = Integer.MAX_VALUE;
        int minDistanceSum = 0;

        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    int distance = Math.abs(target - sum);
                    if (distance < minDistance) {
                        minDistance = distance;
                        minDistanceSum = sum;
                    }
                }
            }
        }


        return minDistanceSum;
    }

    public static void main(String[] args) {
        final ThreeSumClosest threeSumClosest = new ThreeSumClosest();
        System.out.println(threeSumClosest.threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
    }
}
