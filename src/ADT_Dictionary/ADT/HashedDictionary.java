package ADT_Dictionary.ADT;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashedDictionary<K,V> implements DictionaryInterface<K,V>{

    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 5;
    private static final int MAX_CAPACITY = 10000;

    private Entry<K,V>[] hashTable;
    private int tableSize;
    private static final int MAX_SIZE = 2  * MAX_CAPACITY;
    private boolean integrityOK = false;
    private static final double MAX_LOAD_FACTOR = 0.5;

    protected final Entry<K,V> AVAILABLE = new Entry<>(null,null);

    public HashedDictionary(){
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public HashedDictionary(int initialCapacity){
        checkCapacity(initialCapacity);
        numberOfEntries = 0;
        int tableSize = getNextPrime(initialCapacity);
        checkSize(tableSize);
        hashTable = ((Entry<K, V>[]) new Entry[tableSize]);
        integrityOK = true;
    }

    private void checkSize(int anInteger){
        if(!isPrime(anInteger))
            throw new IllegalStateException("The HashSize should not be odd");

    }

    private int getNextPrime(int anInteger){
       if(anInteger % 2 == 0)
           ++anInteger;
       while(!isPrime(anInteger))
           anInteger++;

       return anInteger;
    }

    private boolean isPrime(int anInteger){
        int i = 2;
        boolean answer = true;
        while(i <= anInteger / 2){
            int rem = anInteger % i;
            if(rem != 0)
                i++;
            else
                answer = false;
        }
        return answer;
    }

    private void checkCapacity(int capacity){
        if(capacity > MAX_CAPACITY)
            throw new IllegalStateException("HashTable Object is corrupt.");
    }

    private void checkIntegrity(){
        if(!integrityOK)
            throw new SecurityException("HashTable object is corrupt.");
    }

    private int getHashIndex(K key){
        int hashIndex = key.hashCode() % hashTable.length;
        if(hashIndex < 0)
            hashIndex = hashIndex + hashTable.length;

        return hashIndex;
    }

    private int getLocationUsed(){

        int total = 0;
        for (Entry<K, V> kvEntry : hashTable)
            if ((kvEntry != null) && kvEntry != AVAILABLE)
                total++;

       return total;
    }
    private boolean isHashTableTooFull(){
        return ((double)getLocationUsed() / hashTable.length) >= MAX_LOAD_FACTOR;
    }

    @SuppressWarnings("unchecked")
    private void enlargeHashTable(){
        Entry<K, V>[] oldTable = hashTable;
        int oldSize = hashTable.length;
        int newSize = getNextPrime(oldSize + oldSize);
        checkSize(newSize);
        hashTable = ((Entry<K, V>[]) new Entry[newSize]);
        numberOfEntries = 0;

        for(int i = 0; i < oldSize; i++)
            if((oldTable[i] != null) && oldTable[i] != AVAILABLE){
                add(oldTable[i].getKey(), oldTable[i].getValue());
            }

    }

    private void linearProbe(int index,K key, V value){
        int probeIndex = index;
        while(hashTable[probeIndex] != null && hashTable[probeIndex] != AVAILABLE){
            probeIndex += probeIndex % hashTable.length;
        }
        hashTable[probeIndex] = new Entry<>(key, value);
        numberOfEntries++;
    }


    @Override
    public V add(K key, V value) {
        if((key == null) || (value == null))
            throw new IllegalArgumentException();

        V oldValue;
        int index = getHashIndex(key);

        if(hashTable[index] == null || hashTable[index] == AVAILABLE){
            hashTable[index] = new Entry<>(key, value);
            numberOfEntries++;
            oldValue = null;
        }
        else{
            if(hashTable[index].key == key)
            {
                oldValue = hashTable[index].getValue();
                hashTable[index].setValue(value);
            }
            else{
                oldValue = null;
                linearProbe(index,key, value);
                numberOfEntries++;
            }

        }

        if(isHashTableTooFull())
            enlargeHashTable();

        return oldValue;
    }

    @Override
    public V remove(K key) {
        if(key == null)
            throw new IllegalArgumentException();
        V removedValue = null;
        int index = getHashIndex(key);
        if((hashTable[index] != null) && hashTable[index] != AVAILABLE){
            removedValue = hashTable[index].getValue();
            hashTable[index] = AVAILABLE;
            numberOfEntries--;
        }
        return removedValue;
    }

    @Override
    public V getValue(K key) {
       checkIntegrity();
       V result = null;
       int index = getHashIndex(key);
       if((hashTable[index] != null) && hashTable[index] != AVAILABLE){
           result = hashTable[index].getValue();
       }
       return result;
    }

    @Override
    public boolean contains(K key) {
       if(isEmpty())
           return false;

       int index = getHashIndex(key);
        return (hashTable[index].key.equals(key));
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

    @Override
    public void clear() {
        checkCapacity(DEFAULT_CAPACITY);
        numberOfEntries = 0;
        int tableSize = getNextPrime(DEFAULT_CAPACITY);
        checkSize(tableSize);
        hashTable = ((Entry<K, V>[]) new Entry[tableSize]);
        integrityOK = true;

    }

    private class KeyIterator implements Iterator<K>{

        private int currentIndex;
        private int numberLeft;

        private KeyIterator(){
            currentIndex = 0;
            numberLeft = numberOfEntries;
        }

        @Override
        public boolean hasNext() {
            return numberLeft > 0;
        }

        @Override
        public K next() {
            K result = null;
            if(hasNext()){
                while(hashTable[currentIndex] == null || hashTable[currentIndex] == AVAILABLE){
                    currentIndex++;
                }
                result = hashTable[currentIndex].getKey();
                numberLeft--;
                currentIndex++;
            }
            else
                throw new NoSuchElementException();

            return result;
        }

        public void remove(){
           throw new UnsupportedOperationException();
        }
    }

    private class ValueIterator implements Iterator<V>{

        private int currentIndex;
        private int numberLeft;

        private ValueIterator(){
            currentIndex = 0;
            numberLeft = numberOfEntries;
        }

        @Override
        public boolean hasNext() {
            return numberLeft > 0;
        }

        @Override
        public V next() {
            V result = null;
            if(hasNext()){
                while(hashTable[currentIndex] == null || hashTable[currentIndex] == AVAILABLE){
                    currentIndex++;
                }
                result = hashTable[currentIndex].getValue();
                numberLeft--;
                currentIndex++;
            }
            else
                throw new NoSuchElementException();

            return result;
        }

        public void remove(){
            throw new UnsupportedOperationException();
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
