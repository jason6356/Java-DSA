package ADT_Bag.ADT;

import java.util.Arrays;

public final class ResizeableArrayBag<T> implements BagInterface<T>{

    private T[] bag;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 25;
    private boolean integrityOK;
    private static final int MAX_CAPACITY = 10000;

    public ResizeableArrayBag(){
        this(DEFAULT_CAPACITY);
    }
    @SuppressWarnings("unchecked") //this line suppress compiler warning
    public ResizeableArrayBag(int capacity){
        integrityOK = false;
        if(capacity < MAX_CAPACITY) {
            //create array of obj and cast to T[] type
            bag = (T[]) new Object[capacity];
            numberOfEntries = 0;
            integrityOK = true;
        }
        else
            throw new IllegalStateException("Attempt to create a bag whose " +
                    "capacity exceeds allowed maximum.");
    }

    private void checkIntegrity(){
        if(!integrityOK)
            throw new SecurityException("ArrayBag object is corrupt.");
    }

    @Override
    public int getCurrentSize() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    /**
     * Check if the Array is Full
     * @return True if is full, False if is not full
     */
    private boolean isArrayFull(){
        return numberOfEntries >= bag.length;
    }

    private void checkCapacity(int capacity){
        if(capacity > MAX_CAPACITY)
            throw new IllegalArgumentException("Attempt to create a bag whose " +
                    "capacity exeeds allowed " + "maximum of " + MAX_CAPACITY);

    }

    /**
     * Doubles the size of the array bag
     */
    private void doubleCapacity(){
        int newLength = 2 * bag.length;
        checkCapacity(newLength);
        bag = Arrays.copyOf(bag,newLength);
    }

    @Override
    public boolean add(T newEntry) {

        checkIntegrity();

        if(isArrayFull()){
            doubleCapacity();
        }
        else{
            bag[numberOfEntries] = newEntry;
            numberOfEntries++;
        }
        return true;
    }

    @Override
    public T remove() {
        T result = null;
        if(!isEmpty()){
            result = bag[numberOfEntries - 1];
            bag[numberOfEntries - 1] = null;
            numberOfEntries--;
        }
        return result;
    }

    private void removeGap(int index){
        for(int i = index; i < numberOfEntries - 1; i++){
            bag[i] = bag[i + 1];
        }
    }

    /**
     * Get the index of the anEntry when searching through the bag
     * @param anEntry The Entry to search
     * @return Index of the Entry, -1 if not found
     */
    private int getIndexOf(T anEntry){
        int result = -1;
        for(int i = 0;i < numberOfEntries; i++)
            if(anEntry.equals(bag[i])){
                result = i;
                break;
            }
        return result;
    }

    /**
     * Remove the Entry of the Element but swaping the element with the last element
     * @param index Index of the Found Element
     * @return T : Removed element, Null if the index is not valid
     */
    private T removeEntry(int index){
        T result = null;
        if(!isEmpty() && index >= 0){
            result = bag[index];
            bag[index] = bag[numberOfEntries - 1];
            bag[numberOfEntries - 1] = null;
        }
        return result;
    }

    @Override
    public boolean remove(T anEntry) {
        int index = getIndexOf(anEntry);
        T result = removeEntry(index);
        return anEntry.equals(result);
    }

    @Override
    public void clear() {
        while(!isEmpty())
            remove();
    }

    @Override
    public int getFrequencyOf(T anEntry) {
        int counter = 0;
        for(int i = 0; i < numberOfEntries; i++){
            if(anEntry.equals(bag[i]))
                counter++;
        }
        return counter;
    }

    @Override
    public boolean contains(T anEntry) {
        return getIndexOf(anEntry) > - 1;
    }

    /**
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public T[] toArray() {

        T[] result = ((T[]) new Object[numberOfEntries]);
        for(int i = 0; i < numberOfEntries; i++)
            result[i] = bag[i];

        return result;
    }
}
