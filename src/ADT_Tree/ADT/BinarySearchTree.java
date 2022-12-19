package ADT_Tree.ADT;

public class BinarySearchTree<T extends Comparable<? super T>> extends BinaryTree<T> implements SearchTreeInterface<T> {

    public BinarySearchTree() {
        super();
    }

    public BinarySearchTree(T rootEntry) {
        super();
        setRoot(new BinaryNode<T>(rootEntry));
    }

    public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree) {
        throw new UnsupportedOperationException();
    }

    private T findEntry(BinaryNode<T> rootNode, T anEntry) {

        if (rootNode == null) {
            return null;
        }

        T result = null;
        T rootEntry = rootNode.getData();

        if (anEntry.equals(rootEntry))
            result = rootNode.getData();
        else if (anEntry.compareTo(rootEntry) < 0)
            result = findEntry(rootNode.getLeft(), anEntry);
        else
            result = findEntry(rootNode.getRight(), anEntry);

        return result;
    }

    @Override
    public boolean contains(T anEntry) {
        return getEntry(anEntry) != null;
    }

    @Override
    public T getEntry(T anEntry) {
        return findEntry(getRoot(), anEntry);
    }

    @Override
    public T add(T anEntry) {
        T result = null;

        if (isEmpty()) {
            setRoot(new BinaryNode<>(anEntry));
        } else {
            result = addEntry(getRoot(), anEntry);
        }

        return result;
    }

    private T addEntry(BinaryNode<T> rootNode, T anEntry) {

        T result = null;
        int comparison = rootNode.getData().compareTo(anEntry);

        if (comparison == 0) {
            result = rootNode.getData();
            rootNode.setData(anEntry);
        } else if (comparison > 0) {
            if (rootNode.hasLeftChild()) {
                result = addEntry(rootNode.getLeft(), anEntry);
            } else {
                rootNode.setLeft(new BinaryNode<>(anEntry));
            }
        } else {
            if (rootNode.hasRightChild()) {
                result = addEntry(rootNode.getRight(), anEntry);
            } else {
                rootNode.setRight(new BinaryNode<>(anEntry));
            }
        }
        return result;
    }

    @Override
    public T remove(T anEntry) {
        return null;
    }
}
