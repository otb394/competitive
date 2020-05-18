package datastructure;

import java.util.List;
import java.util.Optional;

public interface Tree1 {
    int size();
    int height();
    List<Tree1> getChildren();
    boolean addChild(Tree1 child);
    Optional<Tree1> getParent();
    void removeChild(Tree1 child);
    void setParent(Tree1 parent);
    boolean isLeaf();
}
