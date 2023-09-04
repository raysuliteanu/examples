package guava;

import com.google.common.graph.Graph;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.Graphs;
import com.google.common.graph.ImmutableGraph;

@SuppressWarnings("UnstableApiUsage")
public class TransitiveClosureExample {
    public static void main(String[] args) {
        final ImmutableGraph<Integer> graph = GraphBuilder.directed()
                .<Integer>immutable()
                .putEdge(1, 2)
                .putEdge(2, 3)
                .build();

        System.out.println(graph);

        final Graph<Integer> closure = Graphs.transitiveClosure(graph);

        System.out.println(closure);
    }
}
