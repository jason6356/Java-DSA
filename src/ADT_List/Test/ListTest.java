package ADT_List.Test;

import ADT_List.ADT.LList;
import ADT_List.ADT.ListInterface;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListTest {

    ListInterface<Integer> test;

    @BeforeEach
    void setUp() {
        test = new LList<>();
        test.add(1);
        test.add(2);
        test.add(3);
        System.out.println("Setting up a list with 1 - 2 - 3");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Successfully ran the test");
    }

    @Test
    void add() {
        test.add(3);
        assertEquals(4,test.getLength());
        assertEquals(3, test.getEntry(4));
    }

    @Test
    void remove() {
        assertThrows(IndexOutOfBoundsException.class, () -> test.remove(100));
        assertEquals(1, test.remove(1));
    }

    @Test
    void clear() {
        test.clear();
        assertEquals(0,test.getLength());
    }

    @Test
    void replace() {
        assertEquals(1,test.getEntry(1));
        test.replace(1,100);
        assertEquals(100, test.getEntry(1));
    }

    @Test
    void toArray() {
        Integer[] testData = {1,2,3};
        assertArrayEquals(testData, test.toArray());
    }

    @Test
    void contains() {
        assertTrue(test.contains(1));
        assertFalse(test.contains(100));
    }

    @Test
    void getLength() {
        assertEquals(3, test.getLength());
    }

    @Test
    void isEmpty() {
        assertFalse(test.isEmpty());
    }
}