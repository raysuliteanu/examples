package graph;

import java.util.Random;

public abstract class GraphTestUtil {
    public static Graph generate(int numberOfVertices, int fanout) {
        Graph graph = new DefaultGraph();

        for (int i = 1; i <= numberOfVertices; ) {
            final SimpleVertex vertex = new SimpleVertex(i);
            for (int j = 0; j < fanout && i < numberOfVertices; j++) {
                graph.insert(Edge.of(vertex, new SimpleVertex(++i)));
            }
        }

        return graph;
    }

    public static Graph generate(int numberOfEdges) {
        Random random = new Random(System.currentTimeMillis());
        GraphBuilder graph = GraphBuilder.defaultGraph();
        for (int i = 0; i < numberOfEdges; i++) {
            int bound = numberOfEdges / 2;
            SimpleVertex first = new SimpleVertex(random.nextInt(bound));
            SimpleVertex second = new SimpleVertex(random.nextInt(bound));
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
