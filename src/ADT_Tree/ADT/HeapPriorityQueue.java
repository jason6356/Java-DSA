package ADT_Tree.ADT;

import ADT_Queue.ADT.PriorityQueueInterface;

public class HeapPriorityQueue<T extends Comparable<? super T>> implements PriorityQueueInterface<T> {

    private MaxHeapInterface<T> pq;

    public HeapPriorityQueue(){
    }

    @Override
    public void add(T newEntry) {

    }

    @Override
    public T remove() {
        return null;
    }

    @Override
    public T peek() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public void clear() {

    }
}
