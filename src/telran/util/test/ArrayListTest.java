package telran.util.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.util.ArrayList;
import telran.util.Collection;

public class ArrayListTest extends ListTest {
    @Override
    Collection<Integer> createCollection() {
        return new ArrayList<>();
    }

    @BeforeEach
    @Override
    void setUp() {
        super.setUp();
    }

    @Test
    @Override
    void iteratorTest() {
        super.iteratorTest();
    }
}
