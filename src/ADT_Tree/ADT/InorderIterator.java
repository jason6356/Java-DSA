package ADT_Tree.ADT;

import ADT_Stack.ADT.LinkedStack;
import ADT_Stack.ADT.StackInterface;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class InorderIterator<T> implements Iterator<T> {

   private StackInterface<BinaryNode<T>> nodeStack;
   private BinaryNode<T> currentNode;

    public InorderIterator(BinaryNode<T> root) {
        nodeStack = new LinkedStack<>();
        currentNode = root;
    }

    @Override
    public boolean hasNext() {
        return !nodeStack.isEmpty() || currentNode != null;
    }

    @Override
    public T next() {
        BinaryNode<T> nextNode = null;

        while (currentNode != null){
            nodeStack.push(currentNode);
            currentNode = currentNode.getLeft();
        }

        if(!nodeStack.isEmpty()){
            nextNode = nodeStack.pop();

            currentNode = nextNode.getRight();
        }
        else
            throw new NoSuchElementException();

        return nextNode.getData();

    }
}
