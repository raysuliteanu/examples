package misc;

import java.util.ArrayList;
import java.util.List;

public class IncreasingMatrixPath {
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        if (m == 1 && n == 1) {
            return 1;
        }

        int maxPath = 0;
        int currentPath = 1;

        List<List<Integer>> adjList = new ArrayList<>(m * n);
        for (int i = 0; i < m * n; i++) {
            adjList.add(i, new java.util.LinkedList<>());
        }

        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                List<Integer> node = adjList.get(count);

                // up
                if (i - 1 >= 0) {
                    node.add(count - n);
                }
                // right
                if (j + 1 < n) {
                    node.add(count + 1);
                }
                // down
                if (i + 1 < m) {
                    node.add(count + n);
                }
                // left
                if (j - 1 >= 0) {
                    node.add(count - 1);
                }
            }
        }

        return maxPath;
    }

    /*
    Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
Output: 4
Explanation: The longest increasing path is [1, 2, 6, 9].

Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
Output: 4
Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
     */
}
