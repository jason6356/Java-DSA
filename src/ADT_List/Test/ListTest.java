package ADT_List.Test;

import ADT_SortedList.ADT.LinkedSortedList;
import ADT_SortedList.ADT.SortedListInterface;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListTest {

    SortedListInterface<Integer> test;

    @BeforeEach
    void setUp() {
        test = new LinkedSortedList<>();
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
        test.add(0);
        assertEquals(4,test.getLength());
        assertEquals(3, test.getPosition(2));
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
        assertEquals(1,test.getPosition(1));
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