package ADT_Stack.ADT;

import java.util.EmptyStackException;
import java.util.Vector;
public final class LinkedStack<T> implements StackInterface<T>{

    private Node topNode;

    @Override
    public void push(T newEntry) {
        Node newNode = new Node(newEntry, topNode);
        topNode = newNode;
    }

    @Override
    public T pop() {
        T result = peek();

        topNode = topNode.getNext();

        return result;
    }

    @Override
    public T peek() {

        if(isEmpty()){
            throw new EmptyStackException();
        }
        else
            return topNode.getData();

    }

    @Override
    public boolean isEmpty() {
        return topNode == null;
    }

    @Override
    public void clear() {
        topNode = null;
    }

    private final class Node {
        private T data;
        private Node next;

        private Node(T data){
            this(data,null);
        }

        private Node(T data, Node next){
            this.data = data;
            this.next = next;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
