package ADT_Stack.Test;

import ADT_Stack.ADT.ArrayStack;
import ADT_Stack.ADT.LinkedStack;
import ADT_Stack.ADT.StackInterface;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    StackInterface<Integer> intStack;

    @BeforeEach
    void setUp() {
        intStack = new ArrayStack<>();
        intStack.push(3);
        intStack.push(2);
        intStack.push(1);
        System.out.println("Generated Stack with Sequence of 3 -> 2 -> 1 where 1 is the top");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Successfully Executed The Test");
    }

    @Test
    void push() {
        intStack.push(10);
        assertEquals(10, intStack.peek());
    }

    @Test
    void pop() {
        assertEquals(1, intStack.pop());
        assertEquals(2, intStack.pop());
        assertEquals(3, intStack.pop());
        assertThrows(EmptyStackException.class, () -> intStack.pop());
    }

    @Test
    void peek() {
        assertEquals(1, intStack.peek());
    }

    @Test
    void isEmpty() {
        assertFalse(intStack.isEmpty());
        intStack.clear();
        assertTrue(intStack.isEmpty());
    }

    @Test
    void clear() {
        intStack.clear();
        assertThrows(EmptyStackException.class, () -> intStack.peek());
    }
}