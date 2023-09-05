package misc;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class CoursePrereqs {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] prerequisite : prerequisites) {
            graph.get(prerequisite[1]).add(prerequisite[0]);
        }

        return topologicalSort(numCourses, graph);
    }

    public static final int WHITE = 0;
    public static final int GRAY = 1;
    public static final int BLACK = 2;

    private int[] topologicalSort(final int graphNodes, final List<List<Integer>> graph) {
        Stack<Integer> stack = new Stack<>();

        int[] visited = new int[graphNodes];
        for (int i = 0; i < graph.size(); i++) {
            if (!(visited[i] == BLACK)) {
                visited[i] = GRAY;
                topologicalSortRecursive(i, graph, stack, visited);
                visited[i] = BLACK;
            }
        }

        if (stack.size() != graphNodes) {
            // cycle
            return new int[]{};
        }

        int[] sorted = new int[stack.size()];
        for (int i = 0; i < sorted.length; i++) {
            sorted[i] = stack.pop();
        }

        return sorted;
    }

    private void topologicalSortRecursive(final int vertex, final List<List<Integer>> graph, final Stack<Integer> stack, final int[] visited) {
        List<Integer> adjListForVertex = graph.get(vertex);
        if (adjListForVertex != null) {
            for (Integer node : adjListForVertex) {
                if (visited[node] == GRAY) {
                    // cycle - already seen this node in current traversal
                    return;
                }

                if (!(visited[node] == BLACK)) {
                    visited[node] = GRAY;
                    topologicalSortRecursive(node, graph, stack, visited);
                    visited[node] = BLACK;
                }
            }
            stack.push(vertex);
        }
    }
}