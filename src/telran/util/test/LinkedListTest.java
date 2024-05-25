package telran.util.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.util.LinkedList;

public class LinkedListTest extends ListTest {
    @BeforeEach
    @Test
    void setUp() {
        collection = new LinkedList<Integer>();
        super.setUp();
    }

}
