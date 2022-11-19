package ADT_Tree.ADT;

import ADT_Tree.Exceptions.EmptyTreeException;

import java.util.Iterator;

public class BinaryTree <T> implements  BinaryTreeInterface<T>{

    private BinaryNode<T> root;

    public BinaryTree(){
        this(null);
    }

    public BinaryTree(T rootData){
        this.root = new BinaryNode<>(rootData);
    }

    public BinaryTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree){
           initializeTree(rootData, leftTree, rightTree);
    }

    private void initializeTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree){

        this.root = new BinaryNode<>(rootData);

        if(leftTree != null && !leftTree.isEmpty())
            root.setLeft(leftTree.root.copy());
        if(rightTree != null && !rightTree.isEmpty())
            root.setRight(rightTree.root.copy());

        if(leftTree != null && leftTree != this)
            leftTree.clear();
        if(rightTree != null && rightTree != this)
            rightTree.clear();
    }


    @Override
    public void setRootData(T rootData) {
        if(rootData == null)
            throw new IllegalArgumentException();

        root.setData(rootData);
    }

    @Override
    public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree) {
        initializeTree(rootData, ((BinaryTree<T>) leftTree), ((BinaryTree<T>) rightTree));
    }
    @Override
    public T getRootData() {

        if(isEmpty())
            throw new EmptyTreeException();
        else
            return root.getData();

    }

    @Override
    public int getHeight() {
        int height = 0;
        if (root != null)
            height = root.getHeight();

        return height;
    }

    @Override
    public int getNumberOfEntries() {
        int numberOfNodes = 0;
        if(root != null)
            numberOfNodes = root.getNumberOfNodes();
        return numberOfNodes;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public Iterator<T> getPreOrderIterator() {
        return new PreOrderIterator<>(root);
    }

    @Override
    public Iterator<T> getPostOrderIterator() {
        return null;
    }

    @Override
    public Iterator<T> getInorderIterator() {
        return new InorderIterator<>(root);
    }

    @Override
    public Iterator<T> getLevelOrderIterator() {
        return null;
    }
}
