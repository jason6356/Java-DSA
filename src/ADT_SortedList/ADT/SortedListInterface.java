package ADT_SortedList.ADT;

public interface SortedListInterface<T extends Comparable<? super T >>{

    /**
     * Adds a new entry to this sorted list in its proper order.
     * This list's size is increased by 1.
     * @param anEntry The object to be added as a new entry.
     */
    public void add(T anEntry);

    /**
     * Removes the first or only occurence of a specified entry
     * from this sorted list.
     * @param anEntry The object to be removed.
     * @return True if anEntry was located and removed;
     *          otherwise returns false.
     */
    public boolean remove(T anEntry);

    /**
     * Gets the position of an entry in this sorted list.
     * @param anEntry AnEntry the object to be found.
     * @return The position of the first or only occurence of anEntry
     *          if it occurs in the list; otherwise returns the position where anEntry
     *          would occur in the list, but as a negative integer.
     */
    public int getPosition(T anEntry);

    public T anEntry(int givenPosition);
    public boolean contains(T anEntry);
    public T remove(int givenPosition);
    public void clear();
    public int getLength();

    public boolean isEmpty();
    public T[] toArray();
}
