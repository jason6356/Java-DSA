package ADT_Queue.ADT;

import java.util.NoSuchElementException;

public final class TwoPartCircularLinkedQueue<T> implements QueueInterface<T>{

    private Node queueNode;
    private Node freeNode;

    public TwoPartCircularLinkedQueue(){
        freeNode = new Node(null,null);
        freeNode.setNext(freeNode);
        queueNode = freeNode;
    }


    private boolean isNextNodeNeeded(){
        return freeNode.next == queueNode;
    }
    @Override
    public void enqueue(T newEntry) {
        freeNode.setData(newEntry);
        if(isNextNodeNeeded()){
            Node newNode = new Node(null, freeNode);
            freeNode.setNext(newNode);
        }
        freeNode = freeNode.getNext();
    }

    @Override
    public T dequeue() {
        T front = getFront();
        queueNode.setData(null);
        queueNode = queueNode.getNext();
        return front;
    }

    @Override
    public T getFront() {
        if(isEmpty())
            throw new NoSuchElementException();
        else
            return queueNode.data;
    }

    @Override
    public boolean isEmpty() {
        return freeNode == queueNode;
    }

    @Override
    public void clear() {
        queueNode = null;
        freeNode = null;
    }

    private final class Node {
        private T data;
        private Node next;

        private Node(T data, Node next){
            this.data = data;
            this.next = next;
        }

        private Node(T data){
            this(data,null);
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
