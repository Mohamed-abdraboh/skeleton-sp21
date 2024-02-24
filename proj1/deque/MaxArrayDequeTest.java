package deque;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class MaxArrayDequeTest {

    @Test
    public void maxTest() {

        MaxArrayDeque<Integer> deque
                = new MaxArrayDeque<>((c1, c2)
                -> (c1 > c2) ? 1 : -1);

        deque.addLast(3);
        deque.addLast(10);
        deque.addLast(100);
        deque.addLast(80);

        assertEquals(Integer.valueOf(100), deque.max());

    }
}
