package datastructure;

/**
 * Represents a graph where each node is numbered from 0 to size-1 and has a value of type V and every edge has value E
 */
public interface Graph0<V,E> {
    int size();

    /**
     * Sets the value of the node at the given index to the given value.
     *
     * @param index
     * @param value
     */
    void setVertex(int index, V value);

    /**
     * Sets an edge from node p (index) to node q (index) with the given value.
     * Replaces the value of an existing edge.
     *
     * @param p node index of first vertex
     * @param q node index of second vertex
     * @param value Value to associate with the edge
     * @param directed true, if the edge is directed, false otherwise
     */
    void setEdge(int p, int q, E value, boolean directed);

    Pair<Integer,E>[] getNeighbors(int p);
}
