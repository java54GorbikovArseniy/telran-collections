package telran.util.test;

import org.junit.jupiter.api.Test;
import telran.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public abstract class SetTest extends CollectionTest{
    Set<Integer> set;

    @Override
    void setUp() {
        super.setUp();
        set = (Set<Integer>) collection;
    }

    @Test
    void getTest(){
        assertEquals(1, set.get(1));
        assertNull(set.get(1000000));
    }

    @Override
    @Test
    void addEqualedTest(){
        assertFalse(set.add(numbers[0]));
    }
}
