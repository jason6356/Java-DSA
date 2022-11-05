package ADT_Stack.ADT;

import java.util.Arrays;
import java.util.EmptyStackException;

public final class ArrayStack<T> implements StackInterface<T>{

    private T[] stack;
    private int topIndex;
    private boolean integrityOK;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;

    public ArrayStack(){
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayStack(int capacity){

        integrityOK = false;
        checkCapacity(capacity);

        stack = ((T[]) new Object[capacity]);
        topIndex = -1;
        integrityOK = true;
    }

    private void checkCapacity(int capacity) {
        if(capacity > MAX_CAPACITY)
            throw new IllegalStateException("The Stack Object is corrupt.");
    }

    private void checkIntegrity(){
        if(!integrityOK)
            throw new SecurityException("The Stack Object is corrupt.");
    }
    
    private  void ensureCapacity(){
        if(topIndex == stack.length - 1){
            int newLength = stack.length * 2;
            checkCapacity(newLength);
            stack = Arrays.copyOf(stack,newLength);
        }
    }

    @Override
    public void push(T newEntry) {
        checkIntegrity();
        ensureCapacity();
        stack[topIndex + 1] = newEntry;
        topIndex++;
    }

    @Override
    public T pop() {
        checkIntegrity();
        T result = peek();
        stack[topIndex] = null;
        topIndex--;
        return result;
    }

    @Override
    public T peek() {
        if(isEmpty())
            throw new EmptyStackException();
        else
            return stack[topIndex];
    }

    @Override
    public boolean isEmpty() {
        return topIndex == -1;
    }

    @Override
    public void clear() {
        topIndex = -1;
    }
}
