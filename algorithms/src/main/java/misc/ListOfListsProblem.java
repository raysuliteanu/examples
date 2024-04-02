package misc;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
Given an array of arrays of integers, return an array of array of integers containing all combinations e.g.
    Example input:
    [[1, 2, 3, 4], [5, 6], [7, 8, 9, 10], [11]]

    Example output:
    [[1, 5, 7, 11], [1, 6, 7, 11], [1, 5, 8, 11], ... ]
 */
public class ListOfListsProblem {
    public static void main(String[] args) {
        int[][] input = {{1, 2, 3, 4}, {5, 6}, {7, 8, 9, 10}, {11}};

        final List<List<Integer>> result = solve(input);
        System.out.printf("result count: %d%n", result.size());
        result.forEach(System.out::println);
    }

    public static List<List<Integer>> solve(int[][] input) {
        List<List<Integer>> results = new ArrayList<>();
        Stack<Integer> combo = new Stack<>();

        solve(input, 0, combo, results);

        return results;
    }

    private static void solve(int[][] input, int rowStart, Stack<Integer> combo, List<List<Integer>> results) {
        for (int rowIndex = rowStart; rowIndex < input.length; rowIndex++) {
            int[] row = input[rowIndex];
            for (int value : row) {
                combo.push(value);
                if (rowIndex + 1 == input.length) {
                    results.add(combo.stream().toList());
                }
                else {
                    solve(input, rowIndex + 1, combo, results);
                }

                combo.pop();
            }
        }

    }
}
