package ADT_Bag.ADT;

public class ArrayBag<T> implements BagInterface<T>{

    private final T[] bag;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 25;

    public ArrayBag(){
        this(DEFAULT_CAPACITY);
    }
    @SuppressWarnings("unchecked") //this line suppress compiler warning
    public ArrayBag(int capacity){
        //create array of obj and cast to T[] type
        bag = (T[]) new Object[capacity];
        numberOfEntries = 0;
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

    @Override
    public boolean add(T newEntry) {
        if(isArrayFull())
            return false;
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

    @Override
    public boolean remove(T anEntry) {
        for(int i = 0; i < numberOfEntries; i++){
            if(anEntry.equals(bag[i])){
                bag[i] = null;
                removeGap(i);
                numberOfEntries--;
                return true;
            }
        }
        return false;
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
        for(int i = 0; i < numberOfEntries; i++)
            if(bag[i].equals(anEntry))
                return true;

        return false;
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
