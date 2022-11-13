package ADT_Dictionary.ADT;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SortedArrayDictionary<K extends Comparable<? super K>, V> implements DictionaryInterface<K,V> {

    private Entry<K, V>[] dictionary;
    private int numberOfEntries;
    private final boolean integrityOK;
    private final static int DEFAULT_CAPACITY = 25;
    private final static int MAX_CAPACITY = 10000;

    public SortedArrayDictionary() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public SortedArrayDictionary(int initialCapacity){
        checkCapacity(initialCapacity);
        dictionary = ((Entry<K, V>[]) new Entry[initialCapacity]);
        numberOfEntries = 0;
        integrityOK = true;
    }
    private void checkIntegrity(){
        if(!integrityOK)
            throw new SecurityException("Dictionary object is corrupt.");
    }

    private void checkCapacity(int capacity){
        if(capacity > MAX_CAPACITY)
            throw new IllegalStateException("Dictionary Object is corrupt.");
    }

    private void ensureCapacity(){

        if(numberOfEntries > dictionary.length){
            int newLength = dictionary.length * 2;
            checkCapacity(newLength);
            dictionary = Arrays.copyOf(dictionary, newLength);
        }
    }

    /**
     * Returns the array of the entry that contains key,
     * or returns number of entries if no such entry exists.
     * or returns the actual level on the index if no such element exist
     */
    private int locateIndex(K key){
       int left = 0;
       int right = numberOfEntries - 1;
       int mid = -1;

       while(left <= right) {

           mid = left + ( right - left) / 2;

           if(dictionary[mid].getKey().compareTo(key) > 0)
               right = mid - 1;
           else if(dictionary[mid].getKey().compareTo(key) < 0)
               left = mid + 1;
           else
               return mid;
       }
       return left;
    }

    private void makeRoom(int position){
        for(int i = numberOfEntries; i > position; i--)
            dictionary[i] = dictionary[i - 1];
    }

    @Override
    public V add(K key, V value) {
        checkIntegrity();
        //Check for valid key and values
        if((key == null) || (value == null))
            throw new IllegalArgumentException();
        else{
            V result = null;
            int keyIndex = locateIndex(key);

            //if the key value exist in the dictionary, we just change the value
            if((keyIndex < numberOfEntries) && key.equals(dictionary[keyIndex].getKey())) {
                result = dictionary[keyIndex].getValue();
                dictionary[keyIndex].setValue(value);
            }
            //else we take it as a new entry
            else{
                makeRoom(keyIndex);
                dictionary[keyIndex] = new Entry<>(key, value);
                numberOfEntries++;
                ensureCapacity();
            }
            return result;
        }
    }

   private void removeArrayEntry(int keyIndex){
       if (numberOfEntries - keyIndex >= 0)
           System.arraycopy(dictionary, keyIndex + 1, dictionary, keyIndex, numberOfEntries - keyIndex);
   }

    @Override
    public V remove(K key) {
        if((key == null))
            throw new IllegalArgumentException();
        else{
            V result = null;
            int keyIndex = locateIndex(key);
            if(keyIndex < numberOfEntries){
                result = dictionary[keyIndex].getValue();
                removeArrayEntry(keyIndex);
                dictionary[numberOfEntries - 1] = null;
                numberOfEntries--;
            }
            return result;
        }

    }


    @Override
    public V getValue(K key) {
        if((key == null))
            throw new IllegalArgumentException();
        else{
            V result = null;
            int keyIndex = locateIndex(key);
            if(keyIndex < numberOfEntries && key.compareTo(dictionary[keyIndex].getKey()) == 0)
                result = dictionary[keyIndex].getValue();

            return result;
        }
    }

    @Override
    public boolean contains(K key) {
        if((key == null))
            throw new IllegalArgumentException();
        else{
            int keyIndex = locateIndex(key);
            return keyIndex < numberOfEntries;
        }
    }

    @Override
    public Iterator<K> getKeyIterator() {
        return new KeyIterator();
    }

    @Override
    public Iterator<V> getValueIterator() {
        return new ValueIterator();
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    @Override
    public int getSize() {
        return numberOfEntries;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        dictionary = ((Entry<K, V>[]) new Entry[DEFAULT_CAPACITY]);
        numberOfEntries = 0;
    }

    @Override
    public String toString() {
        return "SortedArrayDictionary{" +
                "dictionary=" + Arrays.toString(dictionary) +
                '}';
    }

    private class KeyIterator implements Iterator<K>{

        private int currIndex;

        public KeyIterator() {
            this.currIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return currIndex < numberOfEntries;
        }

        @Override
        public K next() {
            if(!hasNext())
                throw new NoSuchElementException();
            else{
                K result = dictionary[currIndex].getKey();
                currIndex++;
                return result;
            }
        }
    }

    private class ValueIterator implements Iterator<V>{

        private int currIndex;

        public ValueIterator() {
            this.currIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return currIndex < numberOfEntries;
        }

        @Override
        public V next() {
            if(!hasNext())
                throw new NoSuchElementException();
            else{
                V result = dictionary[currIndex].getValue();
                currIndex++;
                return result;
            }
        }
    }
    private static class Entry<K,V>{
        private final K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

}

