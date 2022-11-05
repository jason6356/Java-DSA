package ADT_Stack.ADT;
import java.util.EmptyStackException;
public interface StackInterface<T> {

    /**
     * Adds a new entry into the top of the stack
     * @param newEntry An object to be added into the stack.
     */
    public void push(T newEntry);

    /**
     * Removes and returns this stack's top entry.
     * @return The object at the top of the stack.
     * @throws EmptyStackException if the stack is empty before the operation.
     */
    public T pop();

    /**
     * Retrieves this stack's top entry.
     * @return The objec at the top of the stack.
     * @throws EmptyStackException if the stack is empty.
     */
    public T peek();

    /**
     * Detects whether this stack is empty.
     * @return True if the stack is empty
     */
    public boolean isEmpty();

    /**
     * Remvoes all entries from this stack.
     */
    public void clear();

}
