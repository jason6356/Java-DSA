package ADT_List.ADT;

public class LList<T> implements ListInterface<T> {

    private Node firstNode;
    private Node lastNode;
    private int numberOfEntries;

    public LList() {
        initializeDataFields();
    }

    private void initializeDataFields() {
        firstNode = null;
        lastNode = null;
        numberOfEntries = 0;
    }

    private Node getNodeAt(int givenPosition) {

        Node currentNode = firstNode;
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            for (int i = 1; i < givenPosition; i++) {
                currentNode = currentNode.getNext();
            }
        } else {
            throw new IndexOutOfBoundsException("" + "Illegal position given to remove operation");
        }
        return currentNode;
    }

    @Override
    public void add(T newEntry) {
        Node newNode = new Node(newEntry);
        if (isEmpty()) {
            firstNode = newNode;
        }else {
            lastNode.setNext(newNode);
        }
        lastNode = newNode;
        numberOfEntries++;
    }

    @Override
    public void add(int givenPosition, T newEntry) {

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            Node newNode = new Node(newEntry);
            if(isEmpty()){
               firstNode = newNode;
               lastNode = newNode;
            }
            else if (givenPosition == 1) {
                newNode.setNext(firstNode);
                firstNode = newNode;
            }else if (givenPosition == numberOfEntries + 1) {
                lastNode.setNext(firstNode);
                lastNode = newNode;
            } else {
                Node nodeBefore = getNodeAt(givenPosition - 1);
                Node nodeAfter = getNodeAt(givenPosition);
                newNode.setNext(nodeAfter);
                nodeBefore.setNext(newNode);
            }
            numberOfEntries++;
        } else
            throw new IndexOutOfBoundsException("" + "Illegal position given to remove operation");
    }

    @Override
    public T remove(int givenPosition) {
        T result = null;
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            if (givenPosition == 1) {
                result = firstNode.getData();
                firstNode = firstNode.getNext();
                if(numberOfEntries == 1){
                    lastNode = null;
                }
            } else {
                Node nodeBefore = getNodeAt(givenPosition - 1);
                Node nodeToRemove = getNodeAt(givenPosition);
                Node nodeAfter = getNodeAt(givenPosition + 1);
                nodeBefore.setNext(nodeAfter);
                result = nodeToRemove.getData();
                if(givenPosition == numberOfEntries){
                    lastNode = nodeBefore;
                }
            }
            numberOfEntries--;
            return result;
        } else
            throw new IndexOutOfBoundsException("" + "Illegal position given to remove operation");

    }

    @Override
    public void clear() {
        firstNode = null;
        numberOfEntries = 0;
    }

    @Override
    public T replace(int givenPosition, T newEntry) {
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            Node desiredNode = getNodeAt(givenPosition);
            T originalEntry = desiredNode.getData();
            desiredNode.setData(newEntry);
            return originalEntry;
        } else
            throw new IndexOutOfBoundsException("" + "Illegal position given to remove operation");
    }

    @Override
    public T getEntry(int givenPosition) {
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            return getNodeAt(givenPosition).getData();
        } else
            throw new IndexOutOfBoundsException("" + "Illegal position given to remove operation");
    }

    @SuppressWarnings("unchecked")
    @Override
    public T[] toArray() {
        T[] result = ((T[]) new Object[numberOfEntries]);
        Node currNode = firstNode;
        for (int i = 0; i < numberOfEntries && currNode != null; i++) {
            result[i] = currNode.getData();
            currNode = currNode.getNext();
        }
        return result;
    }

    @Override
    public boolean contains(T anEntry) {
        boolean found = false;
        Node currNode = firstNode;
        while(!found && currNode!= null){
            if(anEntry.equals(currNode.getData()))
                found = true;
            else
                currNode = currNode.getNext();
        }
        return found;
    }

    @Override
    public int getLength() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0 && firstNode == null;
    }

    private class Node {
        private T data;
        private Node next;

        private Node(T data) {
            this(data, null);
        }

        private Node(T data, Node next) {
            this.data = data;
            this.next = next;
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
