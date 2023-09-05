package misc;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Coprimes {
    public static void main(String[] args) {
        int[] nums = {2, 3, 3, 2};
        int[][] edges = {{0, 1}, {1, 2}, {1, 3}};
        final int[] coprimes = new Coprimes().getCoprimes(nums, edges);
        System.out.println(Arrays.toString(coprimes));

    }

    public int[] getCoprimes(int[] nums, int[][] edges) {

        Graph g = new Graph(nums, edges);

        int[] coprimes = new int[nums.length];
        Arrays.fill(coprimes, -1);

        List<Edge> edgesForVertex = g.edgesPerVertex.get(0);
        if (edgesForVertex != null) {
            for (Edge e : edgesForVertex) {
                findCoprimes(g, e, coprimes);
            }
        }

        return coprimes;
    }

    void findCoprimes(Graph g, Edge edge, int[] coprimes) {
        final Vertex vertex = edge.vertices[1];
        final int vertexId = vertex.id;
        List<Edge> edges = g.edgesPerVertex.get(vertexId);
        if (edges != null) {
            for (Edge e : edges) {
                findCoprimes(g, e, coprimes);
            }
        }

        Vertex parentVertex = edge.vertices[0];
        if (gcd(parentVertex, vertex) == 1) {
            coprimes[vertexId] = parentVertex.id;
        } else {
            boolean foundGcd = false;
            while (parentVertex != null && !foundGcd) {
                final List<Edge> list = g.edgesPerVertex.get(parentVertex.id);
                for (Edge e : list) {
                    if (e.vertices[1].id == parentVertex.id) {
                        if (gcd(e.vertices[0], parentVertex) == 1) {
                            coprimes[vertexId] = e.vertices[0].id;
                            foundGcd = true;
                        } else {
                            parentVertex = e.vertices[0];
                        }

                        break;
                    }
                }
                if (parentVertex == edge.vertices[0]) {
                    break;
                }
            }
        }
    }

    private int gcd(final Vertex parentVertex, final Vertex vertex) {
        final BigInteger v1 = BigInteger.valueOf(parentVertex.weight);
        final BigInteger v2 = BigInteger.valueOf(vertex.weight);
        return v1.gcd(v2).intValue();
    }

    static class Graph {
        final Vertex[] vertices;
        final Map<Integer, List<Edge>> edgesPerVertex = new HashMap<>();

        public Graph(int[] weights, int[][] edges) {
            vertices = new Vertex[weights.length];
            for (int i = 0; i < weights.length; i++) {
                vertices[i] = new Vertex(i, weights[i]);
            }

            for (int[] edge : edges) {
                Edge e = Edge.of(vertices[edge[0]], vertices[edge[1]]);
                List<Edge> list = edgesPerVertex.computeIfAbsent(edge[0], ArrayList::new);
                list.add(e);
            }
        }
    }

    static class Vertex {
        int id;
        int weight;

        public Vertex(int id, int weight) {
            this.id = id;
            this.weight = weight;
        }
    }

    static class Edge {
        Vertex[] vertices = new Vertex[2];

        public Edge(Vertex v1, Vertex v2) {
            vertices[0] = v1;
            vertices[1] = v2;
        }

        public static Edge of(Vertex v1, Vertex v2) {
            return new Edge(v1, v2);
        }
    }
}