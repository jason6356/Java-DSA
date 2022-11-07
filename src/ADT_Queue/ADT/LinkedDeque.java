package ADT_Queue.ADT;

import java.util.NoSuchElementException;

public final class LinkedDeque<T> implements DequeInterface<T> {

    private DLNode firstNode;
    private DLNode lastNode;

    public LinkedDeque(){
        firstNode = null;
        lastNode = null;
    }

    @Override
    public void addToFront(T newEntry) {
        DLNode newNode = new DLNode(newEntry);
        /*
         * If it is empty deque, the first node can also say as the last node
         */
        if(isEmpty())
            lastNode = newNode;
        else {
            firstNode.setPrev(newNode);
            newNode.setNext(firstNode);
        }

        firstNode = newNode;
    }

    @Override
    public void addToBack(T newEntry) {

        DLNode newNode = new DLNode(newEntry);
        /*
          If it is empty deque, the first node can also say as the front node
          therefore we assign newNode as frontNode,
          other words when adding a new Node to the back of the deque
          the new freeNode will become the lastNode
         */
        if(isEmpty())
            firstNode = newNode;
        else {
            lastNode.setNext(newNode);
            newNode.setPrev(lastNode);
        }

        lastNode = newNode;
    }

    @Override
    public T removeFront() {

        T front = getFront();

        firstNode = firstNode.getNext();
        if(firstNode == null)
            lastNode = null;
        else
            firstNode.setPrev(null);

        return front;
    }

    @Override
    public T removeBack() {
        T back = getBack();

        lastNode = lastNode.getPrev();
        if(lastNode == null)
            firstNode = null;
        else
            lastNode.setNext(null);

        return back;
    }

    @Override
    public T getFront() {
        if(isEmpty())
            throw new NoSuchElementException();
        else
            return firstNode.getData();
    }

    @Override
    public T getBack() {
        if(isEmpty())
            throw new NoSuchElementException();
        else
            return lastNode.getData();
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




    public final class DLNode {
        private T data;
        private DLNode next;
        private DLNode prev;

        private DLNode(T data, DLNode next, DLNode prev){
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

        private DLNode(T data){
            this(data,null,null);
        }

        public T getData() {
            return data;
        }

        public DLNode getNext() {
            return next;
        }

        public void setNext(DLNode next) {
            this.next = next;
        }

        public DLNode getPrev() {
            return prev;
        }

        public void setPrev(DLNode prev) {
            this.prev = prev;
        }
    }
}
