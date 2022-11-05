package ADT_Bag.Test;

import ADT_Bag.ADT.ArrayBag;
import ADT_Bag.ADT.BagInterface;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class ArrayBagTest {

    BagInterface<Integer> intBag;

    @BeforeEach
    void setUp() {
        System.out.println("Initializing Bag ADT with 3 Elements");
        intBag = new ArrayBag<>();
        intBag.add(3);
        intBag.add(5);
        intBag.add(10);
    }

    @AfterEach
    void successMessage(){
        System.out.println("Successfully Run The Test");
    }

    @Test
    @DisplayName("Testing the get current size method")
    void getCurrentSize() {
        System.out.println("Testing getCurrentSize() Method");
        assertEquals(3, intBag.getCurrentSize());
    }

    @Test
    @DisplayName("Testing the bag is empty")
    void isEmpty() {
        System.out.println("Testing isEmpty() Method");
        assertFalse(intBag.isEmpty());
    }

    @Test
    @DisplayName("Add Method")
    void add() {
        System.out.println("Testing Add() Method");
        System.out.println("Testing with duplicate elements");
        assertEquals(true, intBag.add(5));
        for(int i = 1; i <= 25; i++){
            intBag.add(i);
        }
        System.out.println("Testing with when reach max capacity");
        assertFalse(intBag.add(100));
    }

    @Test
    void remove() {
        System.out.println("Testing Remove() Method");
        Integer result = intBag.remove();
        assertEquals(10, result);
        System.out.println("Testing Remove(anEntry) Method");
        assertFalse(intBag.remove(10));
        System.out.println("Testing Remove() when the bag is empty");
        while(!intBag.isEmpty()) {
            intBag.remove();
        }
        assertEquals(null, intBag.remove());
    }

    @Test
    void clear() {
        System.out.println("Testing Clear() Method");
        intBag.clear();
        assertTrue(intBag.isEmpty());
    }

    @Test
    void getFrequencyOf() {
        System.out.println("Testing getFrequencyOf() Method");
        int[] testCases = {
                3,3,5,5,5
        };
        Arrays.stream(testCases)
                .forEach(e -> {
                    System.out.println("Adding " + e + " into the bag\n");
                    intBag.add(e);
                });
        int expectedNumber = 5;
        System.out.println("Testing Frequency of " + expectedNumber);
        assertEquals(4, intBag.getFrequencyOf(expectedNumber));
    }

    @Test
    void contains() {
        System.out.println("Testing contains() method");
        int expectedNumber = 5;
        System.out.println("Testing the bag contains of " + expectedNumber);
        assertTrue(intBag.contains(expectedNumber));
        expectedNumber = 100;
        System.out.println("Testing the bag contains of " + expectedNumber);
        assertFalse(intBag.contains(expectedNumber));
    }

    @Test
    void toArray() {
        System.out.println("Testing toArray() method");
        Integer[] expectedArray = {
                3,5,10
        };
        assertArrayEquals(expectedArray, intBag.toArray());

    }
}