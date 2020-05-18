package datastructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Trie data structure
 *
 * @param <V> The class representing node of the trie. Should be a subclass of Node class
 * @param <E> The class used to denote edges
 */
public class Trie<V extends Trie.Node<V,E>, E> {
    private V root;
    private Supplier<V> nodeSupplier;

    /**
     * Constructor
     *
     * @param nodeSupplier The supplier to be used to create a new node, effectively determining the mapping strategy.
     */
    public Trie(Supplier<V> nodeSupplier) {
        this.nodeSupplier = nodeSupplier;
        this.root = nodeSupplier.get();
    }

    /**
     * Retrieves a node from the trie on a given path
     *
     * @param path The path as list of edges
     * @return Returns the node element, if found. Returns null, otherwise.
     */
    public V getNode(List<E> path) {
        V current = root;
        for (E edge: path) {
            V next = current.nextMap.get(edge);
            if (next == null) {
                return null;
            }
            current = next;
        }
        return current;
    }

    /**
     * Puts the given node in the trie at the path location.
     * Adds intermediate nodes if needed.
     *
     * @param path Path as list of edges
     * @param node The node to be put
     */
    public void putNode(List<E> path, V node) {
        V current = root;
        int len = path.size();
        for (int i = 0; i < len; i++) {
            E edge = path.get(i);
            int finalI = i;
            V next = Optional.ofNullable(current.nextMap.get(edge))
                    .orElseGet(() -> ((finalI == len - 1) ? (node) : nodeSupplier.get()));
            current.nextMap.put(edge, next);
            current = next;
        }
    }

    /**
     * Returns the root of the trie
     */
    public V getRoot() {
        return root;
    }

    /**
     * Returns a stream of all nodes in BFS visit order
     */
    public Stream<V> stream() {
        Queue<V> queue = new LinkedList<>();
        Set<V> discovered = new HashSet<>();
        discovered.add(root);
        queue.add(root);
        List<V> ret = new ArrayList<>();
        while(!queue.isEmpty()) {
            V element = queue.poll();
            ret.add(element);
            for (V child: element.nextMap.values()) {
                if (!discovered.contains(child)) {
                    discovered.add(child);
                    queue.add(child);
                }
            }
        }
        return ret.stream();
    }

    public static class Node<V, E> {
        public Map<E, V> nextMap;

        public Node() {
            this.nextMap = new HashMap<>();
        }

        public void put(E edge, V node) {
            nextMap.put(edge, node);
        }

        /**
         * Retrieves the child node on the given edge, if found. Returns null, otherwise.
         */
        public V get(E edge) {
            return nextMap.get(edge);
        }

        /**
         * Returns all the child nodes of this node as a Stream. Same order as internal map.
         */
        public Stream<V> getChildren() {
            return nextMap.values().stream();
        }
    }
}
