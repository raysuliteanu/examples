package misc;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Bfs {
    static int[] bfs(int n, int m, int[][] edges, int s) {
        int[] counts = new int[n];
        Arrays.fill(counts, -1);
        counts[s - 1] = 0;

        boolean[] visited = new boolean[n];

        List<Integer> nodes = new LinkedList<>();
        nodes.add(s);

        while (!nodes.isEmpty()) {
            int node = nodes.remove(0);
            System.out.println("visiting " + node);
            if (!visited[node - 1]) {
                visited[node - 1] = true;
                for (int[] edge : edges) {
                    int otherNode = 0;
                    int weight = 0;
                    if (edge[0] == node) {
                        weight = counts[edge[0] - 1];
                        otherNode = edge[1];
                    } else if (edge[1] == node) {
                        weight = counts[edge[1] - 1];
                        otherNode = edge[0];
                    }

                    if (otherNode > 0 && !visited[otherNode - 1]) {
                        System.out.println("adding node " + otherNode);
                        nodes.add(otherNode);
                        counts[otherNode - 1] = weight + 6;
                    }
                }
            } else {
                System.out.println("already visited " + node);
            }
        }

        return Arrays.stream(counts).filter(value -> value != 0).toArray();
    }
}