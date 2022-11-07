package ADT_Queue.ADT;
import java.util.NoSuchElementException;

public interface DequeInterface <T>{

    /**
     * Adds a new entry to the front/back of this deque.
     * @param newEntry An object to be added.
     */
    public void addToFront(T newEntry);
    public void addToBack(T newEntry);

    /**
     * Removes and returns the front/back entry of this deque.
     * @return The object at the front/back of the deque.
     * @throws NoSuchElementException if the deque is empty.
     */
    public T removeFront();
    public T removeBack();

    /**
     * Retrieves the front/back entry of this deque.
     * @return The object at the front/back of the deque.
     * @throws NoSuchElementException if the deque is empty.
     */
    public T getFront();
    public T getBack();

    /**
     * Detects whether this deque is empty.
     * @return True if the deque is empty, or false otherwise.
     */
    public boolean isEmpty();

    /**
     * Remves all entries from this deque.
     */
    public void clear();

}
