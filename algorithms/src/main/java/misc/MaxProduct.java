package misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MaxProduct {
    public int maxProduct(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        int maxProduct = nums[0];

        for (int i = 0; i < nums.length; i++) {
            int product = nums[i];
            maxProduct = Math.max(product, maxProduct);
            for (int j = i + 1; j < nums.length; j++) {
                product *= nums[j];

                if (product > maxProduct) {
                    maxProduct = product;
                }
            }
        }

        return maxProduct;
    }

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) {
            return 0;
        }

        int prod = 1;
        int count = 0;
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            assert nums[right] > 0 : "impl only works for positive values";

            prod *= nums[right];
            while (prod >= k) {
                // slide left side of the window while "removing" the element that's no longer in the window from the product
                // e.g. if the window bounded by left,right is [10, 9, 2] the product is 180; if k < 180 make the window
                // [9, 2] and "remove" 10 from the product leaving 18 = [9, 2]
                prod /= nums[left++];
            }

            count += right - left + 1;
        }

        return count;
    }

    public static void main(String[] args) {
        final MaxProduct maxProduct = new MaxProduct();

        System.out.println(maxProduct.numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100)); // 8
        System.out.println(maxProduct.numSubarrayProductLessThanK(new int[]{10, 9, 10, 4, 3, 8, 3, 3, 6, 2, 10, 10, 9, 3}, 19)); // 18
/*
        System.out.println(maxProduct.maxProduct(new int[]{2, -5, -2, -4, 3}));
        System.out.println(maxProduct.maxProduct(new int[]{0, -3, -1, 0, 2, 3, -3}));
        System.out.println(maxProduct.maxProduct(new int[]{2, 3, -2, 4}));
        System.out.println(maxProduct.maxProduct(new int[]{-2, 0, -1}));
        System.out.println(maxProduct.maxProduct(new int[]{-2}));
        System.out.println(maxProduct.maxProduct(new int[]{0, 2}));
*/

        final InputStream inputStream = maxProduct.getClass().getClassLoader().getResourceAsStream("numbers.txt");
        if (inputStream != null) {
            final int[] huge = new int[15_000];
            int cnt = 0;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                while (reader.ready()) {
                    huge[cnt++] = Integer.parseInt(reader.readLine());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("read " + cnt + " values from numbers.txt");
            System.out.println(maxProduct.maxProduct(huge));
            System.out.println(maxProduct.numSubarrayProductLessThanK(huge, 100));
        } else {
            System.out.println("can't open numbers.txt");
        }
    }

}

