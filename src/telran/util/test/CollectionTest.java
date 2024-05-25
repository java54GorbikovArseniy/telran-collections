package telran.util.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public abstract class CollectionTest {
    Collection<Integer> collection;
    Integer[] numbers = {-20, 10, 1, 100, -5};

    @BeforeEach
    void setUp() {
        for (Integer num : numbers) {
            collection.add(num);
        }
    }

    @Test
    void iteratorTest() {
        runTest(numbers);
    }

    @Test
    void sizeTest() {
        assertEquals(numbers.length, collection.size());
    }

    protected void runTest(Integer[] expected) {
        Integer[] actual = collection.stream().toArray(Integer[]::new);
        assertArrayEquals(expected, actual);
    }

    @Test
    void removeTest() {
        collection.remove(numbers[2]);
        Integer[] expected = {-20, 10, 100, -5};
        runTest(expected);
    }

    @Test
    void containsTest() {
        assertFalse(collection.contains(999));
        assertTrue(collection.contains(10));
    }
}
