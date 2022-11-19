package ADT_Tree.ADT;
import java.util.Iterator;

public interface TreeIteratorInterface<T> {
    public Iterator<T> getPreOrderIterator();
    public Iterator<T> getPostOrderIterator();
    public Iterator<T> getInorderIterator();
    public Iterator<T> getLevelOrderIterator();
}
