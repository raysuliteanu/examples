package graph;

import java.util.Iterator;
import java.util.Stack;

import static graph.Vertex.of;

public class TopologicalSort {
    public static final int GRAY = 1;
    public static final int BLACK = 2;

    public static int[] topologicalSort(final int graphNodes, final Graph graph) {
        Stack<Integer> stack = new Stack<>();

        int[] visited = new int[graphNodes];
        for (int i = 0; i < graph.countVertices(); i++) {
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

    private static void topologicalSortRecursive(final int vertex, final Graph graph, final Stack<Integer> stack, final int[] visited) {
        final Iterator<Edge> iterator = graph.adjacencyList(of(vertex));
        while (iterator.hasNext()) {
            Edge edge = iterator.next();

            final int node = edge.toVertex().number();

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
