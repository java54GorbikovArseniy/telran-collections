package telran.util.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.util.ArrayList;
import telran.util.Collection;

public class ArrayListTest extends ListTest {
    @BeforeEach
    @Override
    void setUp() {
        collection = new ArrayList<>();
        super.setUp();
    }
}
