package code.datastructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class AdjacencyList<V,E> implements Graph<V,E> {
    private int size;
    private List<V> vertices;
    private List<LinkedList<Pair<Integer,EdgeNode<E>>>> edges;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void setVertex(int index, V value) {
        vertices.set(index, value);
    }

    @Override
    public void setEdge(int p, int q, E value, boolean directed) {
        edges.get(p).addFirst(Pair.of(q, new EdgeNode<>(value, directed)));
    }

    @Override
    public Pair<Integer, E>[] getNeighbors(int p) {
        return null;
    }

    public AdjacencyList(final int size, V[] vertices) {
        this.size = size;
        this.vertices = Arrays.asList(vertices);
        this.edges = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            edges.add(new LinkedList<>());
        }
    }

    private static class EdgeNode<E> {
        private E value;
        private boolean directed;

        public E getValue() {
            return value;
        }

        public boolean getDirected() {
            return directed;
        }

        public EdgeNode(E value, boolean directed) {
            this.value = value;
            this.directed = directed;
        }
    }
}
