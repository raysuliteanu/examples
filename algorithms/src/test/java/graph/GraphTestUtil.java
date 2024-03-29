package graph;

import java.util.Random;

public abstract class GraphTestUtil {
    public static Graph generateRandomGraph(int numberOfEdges) {
        Random random = new Random(System.currentTimeMillis());
        GraphBuilder graph = GraphBuilder.defaultGraph();
        for (int i = 0; i < numberOfEdges; i++) {
            int bound = numberOfEdges / 2;
            Vertex<?> first = Vertex.of(random.nextInt(bound));
            Vertex<?> second = Vertex.of(random.nextInt(bound));
            Edge edge = new Edge(first, second);
            graph.withEdge(edge);
        }
        return graph.build();
    }

    public static void main(String[] args) {
        Graph graph = GraphTestUtil.generateRandomGraph(50);
        System.out.println(graph);
    }
}
