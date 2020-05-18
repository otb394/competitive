package datastructure;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class NaryTree0<V extends NaryTree0.NaryTreeNode0<V>> implements Tree0<V> {
    private V root;
    @Override
    public V getRoot() {
        return root;
    }

    public abstract static class NaryTreeNode0<V extends NaryTreeNode0<V>> extends TreeNode0<V> {
        public Set<V> children;
        public V parent;

        public NaryTreeNode0(Set<V> children, V parent) {
            this.children = children;
            this.parent = parent;
        }

        public NaryTreeNode0() {
            this.children = new HashSet<>();
            this.parent = null;
        }

        @Override
        public List<V> getChildren() {
            return new ArrayList<>(children);
        }

        @Override
        public Optional<V> getParent() {
            return Optional.ofNullable(parent);
        }

        @Override
        public boolean isLeaf() {
            return children.isEmpty();
        }

        @Override
        public boolean addChild(V child) {
            Optional<V> earlierParent = child.getParent();
            earlierParent.ifPresent(this::removeChild);
            child.parent = this.identity();
            children.add(child);
            return true;
        }

        @Override
        public void removeChild(V child) {
            children.remove(child);
        }
    }
}
