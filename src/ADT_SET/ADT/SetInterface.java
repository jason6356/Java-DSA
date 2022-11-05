package ADT_SET.ADT;

public interface SetInterface<T> {

   public int getCurrentSize();

   public boolean isEmpty();

   /**
    * Adds a new entry to this set, avoiding duplicates.
    *
    * @param newEntry The object to be added as a new entry.
    * @return True if the addition is successful, or
    * False if the item is already is in the set.
    */
   public boolean add(T newEntry);

   /**
    * Removes a specific entry from this set, if possible.
    *
    * @param anEntry The entry to be removed.
    * @return True if the removal was successful, or false if not.
    */
   public boolean remvoe(T anEntry);

   /**
    * Remove a unspecific entry from this set, if possible.
    *
    * @return Entry if the removal was successful, or null if the set is empty.
    */
   public T remove();

   /**
    * Clear all the entries inside the set
    */
   public void clear();

   /**
    * Tests whether this bag contains a given entry.
    *
    * @param anEntry The entry to find
    * @return True if the bag contains anEntry, or false if not
    */
   public boolean contains(T anEntry);

   /**
    * Retrieves all entries that are in this bag.
    *
    * @return A newly allocated array of all the entries in the bag.
    * Note: If the bag is empty, the returned array is empty.
    */
   public T[] toArray();
}
