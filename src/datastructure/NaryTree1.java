package datastructure;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class NaryTree1 implements Tree1 {
    private int size;
    private int height;
    private Set<Tree1> children;
    private Tree1 parent;

    public NaryTree1() {
        this.size = 1;
        this.height = 0;
        this.children = new HashSet<>();
        this.parent = null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height;
    }

    @Override
    public List<Tree1> getChildren() {
        return new ArrayList<>(children);
    }

    @Override
    public boolean addChild(Tree1 child) {
        Optional<Tree1> earlierParent = child.getParent();
        earlierParent.ifPresent(pr -> pr.removeChild(child));
        child.setParent(this);
        children.add(child);
        return true;
    }

    @Override
    public Optional<Tree1> getParent() {
        return Optional.ofNullable(parent);
    }

    @Override
    public void removeChild(Tree1 child) {
        children.remove(child);
    }

    @Override
    public void setParent(Tree1 parent) {
        this.parent = parent;
    }

    @Override
    public boolean isLeaf() {
        return children.isEmpty();
    }
}
