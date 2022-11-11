package ADT_SortedList.ADT;

public class LinkedSortedList<T extends Comparable<? super T>> implements SortedListInterface<T>{

    private Node firstNode;
    private  int numberOfEntries;

    public LinkedSortedList() {
       firstNode = null;
       numberOfEntries = 0;
    }

    @Override
    public void add(T anEntry) {

        Node newNode = new Node(anEntry);
        Node nodeBefore = getNodeBefore(anEntry);

        if(isEmpty() || (nodeBefore == null)){
            newNode.setNext(firstNode);
            firstNode = newNode;
        }
        else{
            Node nodeAfter = nodeBefore.getNext();
            nodeBefore.setNext(newNode);
            newNode.setNext(nodeAfter);
        }
        numberOfEntries++;
    }

    private Node getNodeBefore(T anEntry){

        Node curr = firstNode;
        Node nodeBefore = curr;
        while((curr != null) && (anEntry.compareTo(curr.getData()) > 0)){
            nodeBefore = curr;
            curr = curr.getNext();
        }
        return nodeBefore;
    }

    @Override
    public boolean remove(T anEntry) {

       boolean success = false;

       if(!isEmpty() && contains(anEntry)){

           Node nodeBefore = getNodeBefore(anEntry);
           Node nodeAfter = nodeBefore.getNext().getNext();
           nodeBefore.setNext(nodeAfter);
           success = true;
           numberOfEntries--;
       }
        return success;
    }

    @Override
    public int getPosition(T anEntry) {
        int position = 0;
        if(isEmpty() || !contains(anEntry)) {
            position = -1;
        }
        else{
           position = 1;
           Node curr = firstNode;
           while((curr != null) && anEntry.compareTo(curr.getData()) > 0){
              curr = curr.getNext();
              position++;
           }
        }
       return position;
    }

    @Override
    public T anEntry(int givenPosition) {

        Node curr = firstNode;

        if(isEmpty() || givenPosition > numberOfEntries || givenPosition < 1)
            return null;
        else{
            int currPosition = 1;

            while((curr != null) && currPosition < givenPosition){
                curr = curr.getNext();
                currPosition++;
            }

        }
       return curr.getData();
    }

    @Override
    public boolean contains(T anEntry) {
        Node nodeBefore = getNodeBefore(anEntry);

        if(isEmpty())
            return false;
        else if(anEntry.compareTo(nodeBefore.getData()) == 0)
            return true;
        else if(nodeBefore.getNext() != null)
            return anEntry.compareTo(nodeBefore.getNext().getData()) == 0;
        else
            return false;
   }

    @Override
    public T remove(int givenPosition) {
        Node curr = firstNode;
        if(isEmpty() || givenPosition > numberOfEntries || givenPosition < 1){
            throw new IndexOutOfBoundsException();
        }
        else{
            int currPosition = 1;
            Node nodeBefore = curr;
            while((curr != null) && currPosition < givenPosition){
                nodeBefore = curr;
                curr = curr.getNext();
                currPosition++;
            }
            Node nodeAfter = nodeBefore.getNext().getNext();
            nodeBefore.setNext(nodeAfter);
            numberOfEntries--;
        }
        return curr.getData();
    }

    @Override
    public void clear() {
        firstNode = null;
        numberOfEntries = 0;
    }

    @Override
    public int getLength() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return firstNode == null && numberOfEntries == 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T[] toArray() {

        T[] result = ((T[]) new Comparable[numberOfEntries]);
        int i = 0;

        Node curr = firstNode;
        while(curr != null){
           result[i] = curr.getData();
           i++;
           curr = curr.getNext();
        }
        return result;
    }

    private class Node {
        private T data;
        private Node next;

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Node(T data) {
            this(data,null);
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
