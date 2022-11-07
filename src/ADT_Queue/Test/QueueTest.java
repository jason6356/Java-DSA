package ADT_Queue.Test;

import ADT_Queue.ADT.ArrayQueue;
import ADT_Queue.ADT.LinkedQueue;
import ADT_Queue.ADT.QueueInterface;
import ADT_Queue.ADT.TwoPartCircularLinkedQueue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {

    QueueInterface<Integer> q;

    @BeforeEach
    void setUp() {
        q = new TwoPartCircularLinkedQueue<>();
        q.enqueue(3);
        q.enqueue(2);
        q.enqueue(1);
        System.out.println("Setting a Queue with elements of 3 <- 2 <- 1");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Successfully ran the test");
    }

    @Test
    void dequeue() {
        Integer result = q.dequeue();
        assertEquals(3, result);
    }

    @Test
    void getFront() {
        assertEquals(3, q.getFront());
    }

    @Test
    void isEmpty() {
        assertFalse(q.isEmpty());
    }

    @Test
    void clear() {
        q.clear();
        assertTrue(q.isEmpty());
    }
}