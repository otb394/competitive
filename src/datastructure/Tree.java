package datastructure;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Stream;

public class Tree<V extends Tree.TreeNode<V>> {
    public V root;

    public Tree(V root) {
        this.root = root;
    }

    public void addChild(V node, V child) {
        child.setParent(node);
        node.addChild(child);
    }

    /**
     * Returns a stream of all nodes in BFS visit order
     */
    public Stream<V> stream() {
        Stream.Builder<V> builder = Stream.builder();
        V rt = root;
        builder.add(root);
        Queue<V> queue = new LinkedList<>();
        queue.add(rt);
        while(!queue.isEmpty()) {
            V u = queue.poll();
            Set<V> children = u.children;
            for (V child: children) {
                queue.add(child);
                builder.accept(child);
            }
        }
        return builder.build();
    }

    public static class TreeNode<V extends TreeNode<V>> {
        public int size;
        public int height;
        private V parent;
        public Set<V> children;

        public TreeNode() {
            this.size = 1;
            this.height = 0;
            this.parent = null;
            this.children = new HashSet<>();
        }

        public TreeNode(int size, int height, V parent) {
            this.size = size;
            this.height = height;
            this.parent = parent;
            this.children = new HashSet<>();
        }

        public Optional<V> getParent() {
            return Optional.ofNullable(parent);
        }

        public void setParent(V parent) {
            this.parent = parent;
        }

        public boolean isLeaf() {
            return children.isEmpty();
        }

        public void addChild(V child) {
            children.add(child);
            size += child.size;
        }
    }
}
