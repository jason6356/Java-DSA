package ADT_Dictionary.Test;

import ADT_Dictionary.ADT.DictionaryInterface;
import ADT_Dictionary.ADT.HashedDictionary;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DictionaryTest {
    DictionaryInterface<Integer,String> dictionary;

    @BeforeEach
    void setUp() {
        dictionary = new HashedDictionary<>();
        dictionary.add(1,"Abu");
        dictionary.add(0,"Ali");
        dictionary.add(2,"Ahmad");
        System.out.println("Setting Up Dictionary Of Values Of");
        String str = """
               key = 0, Value = Ali
               key = 1, Value = Abu
               key = 2, Value = Ahmad\040
                """;

        System.out.println(dictionary.toString());
    }

    @AfterEach
    void tearDown() {
        System.out.println("Successfully Run The Test");
    }

    @Test
    void add() {
        Integer key = 2;
        String val = "Siti";
        assertEquals("Ahmad", dictionary.getValue(key));
        dictionary.add(key,val);
        assertEquals(3, dictionary.getSize());
        assertEquals("Siti", dictionary.getValue(key));
        assertFalse(dictionary.isEmpty());
    }

    @Test
    void remove() {
       Integer key = 1;
       assertEquals("Abu", dictionary.remove(key));
       assertEquals(2, dictionary.getSize());
       assertThrows(IllegalArgumentException.class,() -> dictionary.remove(null));
       assertNull(dictionary.remove(100));
    }

    @Test
    void contains() {
        assertTrue(dictionary.contains(2));
        assertFalse(dictionary.contains(100));
    }

    @Test
    void getKeyIterator() {
        Iterator<Integer> iterator = dictionary.getKeyIterator();
        List<Integer> ls = new ArrayList<>(Arrays.asList(0,1,2));
        int index = 0;
        while(index < ls.size() && iterator.hasNext()) {
            assertEquals(ls.get(index), iterator.next());
            index++;
        }
    }

    @Test
    void getValueIterator() {
        Iterator<String> iterator = dictionary.getValueIterator();
        List<String> ls = new ArrayList<>(Arrays.asList("Ali","Abu","Ahmad"));
        int index = 0;
        while(index < ls.size() && iterator.hasNext()) {
            assertEquals(ls.get(index), iterator.next());
            index++;
        }
    }

    @Test
    void clear() {
        dictionary.clear();
        assertTrue(dictionary.isEmpty());
        assertFalse(dictionary.contains(0));
    }
}