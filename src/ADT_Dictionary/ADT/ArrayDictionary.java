package ADT_Dictionary.ADT;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayDictionary <K,V> implements DictionaryInterface<K,V>{

   private Entry<K, V>[] dictionary;
   private int numberOfEntries;
   private boolean integrityOK;
   private final static int DEFAULT_CAPACITY = 25;
   private final static int MAX_CAPACITY = 10000;

    public ArrayDictionary() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayDictionary(int initialCapacity){
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
     */
    private int locateIndex(K key){
        int index = 0;
        while((index < numberOfEntries) && !key.equals(dictionary[index].getKey()))
            index++;

        return index;
    }

    @Override
    public V add(K key, V value) {
        checkIntegrity();
        if((key == null) || (value == null))
            throw new IllegalArgumentException();
        else{
            V result = null;
            int keyIndex = locateIndex(key);
            //if the key value exist in the dictionary, we just change the value
            if(keyIndex < numberOfEntries) {
               result = dictionary[keyIndex].getValue();
               dictionary[keyIndex].setValue(value);
            }
            //else we take it as a new entry
            else{
                dictionary[numberOfEntries] = new Entry<>(key, value);
                numberOfEntries++;
                ensureCapacity();
            }
            return result;
        }
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
                dictionary[keyIndex] = dictionary[numberOfEntries - 1];
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
            if(keyIndex < numberOfEntries)
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
    private class Entry<K,V>{
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
    }
}
