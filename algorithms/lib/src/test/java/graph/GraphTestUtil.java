package graph;

import java.util.Random;
import java.util.UUID;

public abstract class GraphTestUtil {
    public static Graph generate(int numberOfEdges) {
        Random random = new Random(System.currentTimeMillis());
        GraphBuilder graph = GraphBuilder.defaultGraph();
        for (int i = 0; i < numberOfEdges; i++) {
            int bound = numberOfEdges / 2;
            SimpleVertex first = new SimpleVertex(random.nextInt(bound), UUID.randomUUID().toString());
            SimpleVertex second = new SimpleVertex(random.nextInt(bound), UUID.randomUUID().toString());
            Edge edge = new Edge(first, second);
            graph.withEdge(edge);
        }
        return graph.build();
    }

    public static void main(String[] args) {
        Graph graph = GraphTestUtil.generate(50);
        System.out.println(graph);
    }
}
