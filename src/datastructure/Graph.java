package datastructure;

import java.util.List;

/**
 * Interface for graph implementations
 *
 * @param <V> Represents vertex of the graph
 * @param <E> Represents an edge in the graph. Should be a subclass of Edge inner class
 */
public interface Graph<V, E extends Graph.Edge<V,E>> {
    Graph<V,E> addVertex(V v);
    Graph<V,E> addEdge(E e);
    List<E> getEdges(V vertex);
    List<V> getVertices();
    E getReverse(E e);

    abstract class Edge<V, E extends Edge<V,E>> {
        public V first;
        public V second;

        public Edge(V first, V second) {
            this.first = first;
            this.second = second;
        }

        public abstract E reverse();
    }

    class BaseEdge<V> extends Edge<V, BaseEdge<V>> {
        private BaseEdge(V first, V second) {
            super(first, second);
        }

        public static <A> BaseEdge<A> of(A fir, A sec) {
            return new BaseEdge<>(fir, sec);
        }

        @Override
        public BaseEdge<V> reverse() {
            return BaseEdge.of(second, first);
        }
    }
}
