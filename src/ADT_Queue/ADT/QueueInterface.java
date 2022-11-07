package ADT_Queue.ADT;
import java.util.NoSuchElementException;

public interface QueueInterface<T>{

    /**
     * Adds a new entry to the back of this queue.
     * @param newEntry An object to be added.
     */
    public void enqueue(T newEntry);

    /**
     * Remvoes and returns the entry at the front of this queue.
     * @return The object at the front of the queue.
     * @throws NoSuchElementException if the queue is empty before the operation.
     */
    public T dequeue();

    /**
     * Retrieves the entry at the front of this queue.
     * @return The object at the front of the queue.
     * @throws NoSuchElementException if the queue is empty before the operation.
     */
    public T getFront();

    /**
     * Detects whether the queue is empty.
     * @return True if the queue is empty, or false otherwise.
     */
    public boolean isEmpty();

    /**
     * Removes all entries from the queue.
     */
    public void clear();

}
