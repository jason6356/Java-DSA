package ADT_Queue.ADT;

import java.util.NoSuchElementException;

public final class LinkedQueue<T> implements QueueInterface<T> {

    private Node firstNode;
    private Node lastNode;

    @Override
    public void enqueue(T newEntry) {
        Node newNode = new Node(newEntry);
        if(isEmpty()){
            firstNode = newNode;
        }
        else{
            lastNode.setNext(newNode);
        }
        lastNode = newNode;
    }

    @Override
    public T dequeue() {
        T result = getFront();
        firstNode.setData(null);
        firstNode = firstNode.next;
        //if the queue is empty right on this dequeue
        if(firstNode == null)
            lastNode = null;

        return result;
    }

    @Override
    public T getFront() {
        if(isEmpty())
            throw new NoSuchElementException();
        else
            return firstNode.getData();
    }

    @Override
    public boolean isEmpty() {
        return firstNode == null && lastNode == null;
    }

    @Override
    public void clear() {
        firstNode = null;
        lastNode = null;
    }

    private final class Node {
        private T data;
        private Node next;

        public Node(T data){
            this(data,null);
        }

        public Node(T data, Node next) {
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
