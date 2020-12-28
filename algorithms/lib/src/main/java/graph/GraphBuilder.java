package graph;

public class GraphBuilder {

    private Graph graph;

    public GraphBuilder(final Graph graph) {
        this.graph = graph;
    }

    public Graph build() {
        return graph;
    }

    public static GraphBuilder defaultGraph() {
        return new GraphBuilder(new DefaultGraph());
    }

    public GraphBuilder withEdge(Edge edge) {
        graph.insert(edge);
        return this;
    }
}
