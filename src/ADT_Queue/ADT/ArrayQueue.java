package ADT_Queue.ADT;

import java.util.Arrays;
import java.util.NoSuchElementException;

public final class ArrayQueue<T> implements QueueInterface<T> {

    private T[] queue;
    private int frontIndex;
    private int backIndex;
    private boolean integrityOk;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;

    public ArrayQueue(){
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructor for the Circular Queue with 1 empty space
     * Therefore when allocating the queue with the length, we + 1 slot of the empty slot
     * @param capacity Capacity of the Queue
     */
    @SuppressWarnings("unchecked")
    public ArrayQueue(int capacity){
        //check capacity
        integrityOk = false;
        checkCapacity(capacity);

        T[] tempQueue = ((T[]) new Object[capacity + 1]);
        queue = tempQueue;
        frontIndex = 0;
        backIndex = capacity;
        integrityOk = true;
    }

    private void checkCapacity(int capacity){
        if(capacity > MAX_CAPACITY)
            throw new IllegalStateException("The Queue Object is corrupt.");
    }

    private void checkIntegrity(){
        if(!integrityOk)
            throw new SecurityException("The Queue Object is corrupt.");
    }

    /**
     * Ensure the capacity is enough when performing enqueue.
     * If the array is full, then we upsize the array by 2
     * else we do nothing
     */
    @SuppressWarnings("unchecked")
    private void ensureCapacity(){
        if(frontIndex == (backIndex + 2) % queue.length){
            System.out.println("Capacity Test");
            int oldLength = queue.length;
            int newLength = oldLength * 2;
            checkCapacity(newLength - 1);
            integrityOk = false;
            T[] newQueue = ((T[]) new Object[newLength]);
            // oldLength - 1 = ignore the space one because this is a 1 element spaced circular queue
            for(int i = 0; i < oldLength - 1; i++){
                newQueue[i] = queue[frontIndex];
                frontIndex = (frontIndex + 1) % oldLength;
            }
            frontIndex = 0;
            backIndex = oldLength - 2;
            integrityOk = true;
        }
    }

    @Override
    public void enqueue(T newEntry) {
        checkIntegrity();
        ensureCapacity();
        backIndex = (backIndex + 1) % queue.length;
        queue[backIndex] = newEntry;
    }

    @Override
    public T dequeue() {
        checkIntegrity();
        T front = getFront();
        queue[frontIndex] = null;
        frontIndex = (frontIndex + 1) % queue.length;
        return front;
    }

    @Override
    public T getFront() {
        checkIntegrity();

        if(isEmpty())
            throw new NoSuchElementException();
        else
            return queue[frontIndex];
    }

    @Override
    public boolean isEmpty() {
        return frontIndex == ((backIndex + 1) % queue.length);
    }

    @Override
    public void clear() {
        while(!isEmpty())
            dequeue();
    }
}
