package ADT_Bag.ADT;

public class LinkedBag<T> implements BagInterface<T>{

    private Node firstNode;
    private int numberOfEntries;

    public LinkedBag(){
        firstNode = null;
        numberOfEntries = 0;
    }

    @Override
    public int getCurrentSize() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return firstNode == null;
    }

    @Override
    public boolean add(T newEntry) {
        if(isEmpty()){
            firstNode = new Node(newEntry);
        }
        else{
            Node newNode = new Node(newEntry);
            newNode.next = firstNode;
            firstNode = newNode;
        }
        numberOfEntries++;

        return true;
    }

    @Override
    public T remove() {
        T result = null;
        if(!isEmpty()){
            result = firstNode.data;
            firstNode = firstNode.next;
            numberOfEntries--;
        }
        return result;
    }

    private Node getReferenceTo(T anEntry){
        Node currNode = firstNode;
        while(currNode != null){
            if(anEntry.equals(currNode.data))
                return currNode;
            else
                currNode = currNode.next;
        }
        return currNode;
    }

    @Override
    public boolean remove(T anEntry) {
        boolean result = false;
        Node nodeN = getReferenceTo(anEntry);
        if(nodeN != null) {
            nodeN.data = firstNode.data;
            firstNode = firstNode.next;
            numberOfEntries--;
            result = true;
        }
        return result;
    }

    @Override
    public void clear() {
        firstNode = null;
    }

    @Override
    public int getFrequencyOf(T anEntry) {
        int count = 0;
        Node currNode = firstNode;

        while((count < numberOfEntries) && (currNode != null)){
            if(anEntry.equals(currNode.data))
                count++;

            currNode = currNode.next;
        }
        return count;
    }

    @Override
    public boolean contains(T anEntry) {
        Node currNode = firstNode;

        while(currNode != null){
            if(anEntry.equals(currNode.data))
                return true;

            currNode = currNode.next;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T[] toArray() {
        T[] result = ((T[]) new Object[numberOfEntries]);
        int index = numberOfEntries - 1;
        Node currNode = firstNode;
        while((index >= 0) && (currNode != null)){
            result[index] = currNode.data;
            index--;
            currNode = currNode.next;
        }
        return result;
    }

    private class Node {
        private T data;
        private Node next;

        private Node(T data){
            this(data, null);
        }

        private Node(T data, Node next){
            this.data = data;
            this.next = next;
        }


    }
}
