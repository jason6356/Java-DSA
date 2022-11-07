package ADT_Queue.Test;

import ADT_Queue.ADT.DequeInterface;
import ADT_Queue.ADT.LinkedDeque;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedDequeTest {

    DequeInterface<Integer> dq;

    @BeforeEach
    void setUp() {
        dq = new LinkedDeque<>();
        dq.addToFront(3);
        dq.addToBack(2);
        dq.addToFront(5);
        // 5 - 3 - 2
        System.out.println("Setting a Deque with sequence of 5 - 3 - 2");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Successfully run the test!");
    }

    @Test
    void removeFront() {
        assertEquals(5,dq.removeFront());
        assertEquals(2, dq.getBack());
        assertEquals(3, dq.getFront());
    }

    @Test
    void removeBack() {
        assertEquals(2,dq.removeBack());
        assertEquals(3, dq.getBack());
        assertEquals(5, dq.getFront());
    }

    @Test
    void getFront() {
        assertEquals(5, dq.getFront());
    }

    @Test
    void getBack() {
        assertEquals(2, dq.getBack());
    }

    @Test
    void isEmpty() {
        assertFalse(dq.isEmpty());
        dq.clear();
        assertTrue(dq.isEmpty());
    }

}