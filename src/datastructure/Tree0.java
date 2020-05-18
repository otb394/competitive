package datastructure;

import java.util.List;
import java.util.Optional;

public interface Tree0<V extends Tree0.TreeNode0> {
    V getRoot();

    abstract class TreeNode0<V extends TreeNode0<V>> {
        public int size;
        public int height;

        public abstract List<V> getChildren();
        public abstract Optional<V> getParent();
        public abstract boolean isLeaf();
        public abstract boolean addChild(V child);
        public abstract void removeChild(V child);
        public abstract V identity();
    }
}
