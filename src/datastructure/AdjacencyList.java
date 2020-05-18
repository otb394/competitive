package datastructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Adjacency list implementation of a graph
 */
public class AdjacencyList<V,E extends Graph.Edge<V,E>> implements Graph<V,E> {
    public Map<V, List<E>> adjList;
    private Map<E,E> reverseMap;

    public boolean directed;

    public AdjacencyList() {
        this.adjList = new HashMap<>();
        this.directed = false;
        this.reverseMap = new HashMap<>();
    }

    public AdjacencyList(boolean directed) {
        this.adjList = new HashMap<>();
        this.directed = directed;
        this.reverseMap = new HashMap<>();
    }

    @Override
    public List<E> getEdges(V vertex) {
        return Optional.ofNullable(adjList.get(vertex)).orElseGet(ArrayList::new);
    }

    @Override
    public Graph<V,E> addVertex(V v) {
        List<E> edges = Optional.ofNullable(adjList.get(v)).orElseGet(ArrayList::new);
        adjList.put(v, edges);
        return this;
    }

    @Override
    public Graph<V,E> addEdge(E e) {
        V v1 = e.first;
        V v2 = e.second;
        List<E> edges1 = Optional.ofNullable(adjList.get(v1)).orElseGet(ArrayList::new);
        edges1.add(e);
        adjList.put(v1, edges1);
        if (!directed) {
            E reverseEdge = e.reverse();
            List<E> edges2 = Optional.ofNullable(adjList.get(v2)).orElseGet(ArrayList::new);
            edges2.add(reverseEdge);
            adjList.put(v2, edges2);
            reverseMap.put(e, reverseEdge);
            reverseMap.put(reverseEdge, e);
        }
        return this;
    }

    @Override
    public List<V> getVertices() {
        return new ArrayList<>(adjList.keySet());
    }

    @Override
    public E getReverse(E e) {
        if (!directed) {
            return reverseMap.get(e);
        } else {
            throw new RuntimeException("Error in trying to get reverse edge object in directed graph");
        }
    }
}
