package telran.util.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class ListTest extends CollectionTest {
    List<Integer> list;

    @BeforeEach
    @Override
    void setUp() {
        super.setUp();
        list = (List<Integer>) collection;
    }

    @Test
    void getTest() {
        for (int i = 0; i < numbers.length; i++) {
            assertEquals(numbers[i], list.get(i));
        }
    }

    @Test
    void addByIndexTest() {
        list.add(2, 999);
        Integer[] expected = {-20, 10, 999, 1, 100, -5};
        runTest(expected);
    }

    @Test
    void removeByIndexTest() {
        list.remove(1);
        Integer[] expected = {-20, 1, 100, -5};
        runTest(expected);
    }

    @Test
    void indexOfTest() {
        assertEquals(1, list.indexOf(10));
        assertEquals(-1, list.indexOf(999));
    }

    @Test
    void lastIndexOfTest() {
        list.add(10);
        assertEquals(5, list.lastIndexOf(10));
    }
}
