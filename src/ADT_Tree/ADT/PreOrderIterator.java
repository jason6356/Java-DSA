package ADT_Tree.ADT;

import ADT_Stack.ADT.LinkedStack;
import ADT_Stack.ADT.StackInterface;

import java.util.Iterator;

public class PreOrderIterator<T> implements Iterator<T> {

    StackInterface<BinaryNode<T>> st = new LinkedStack<>();

    PreOrderIterator(BinaryNode<T> root){
       st.push(root);
    }

    @Override
    public boolean hasNext() {
        return !st.isEmpty();
    }

    @Override
    public T next() {

        BinaryNode<T> node = st.pop();
        if(node.hasRightChild())
            st.push(node.getRight());
        if(node.hasLeftChild())
            st.push(node.getLeft());

        return node.getData();
    }
}
