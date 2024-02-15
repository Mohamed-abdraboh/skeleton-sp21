package deque;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class ArrayDequeTest {
    @Test
    public void testAddLastAndRemove() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);

        assertEquals(Integer.valueOf(3), deque.removeFirst());
        assertEquals(Integer.valueOf(2), deque.removeFirst());
        assertEquals(Integer.valueOf(1), deque.removeFirst());
        assertTrue(deque.isEmpty());
    }

    @Test
    public void testAddFrontAndRemove() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);

        assertEquals(Integer.valueOf(3), deque.removeFront());
        assertEquals(Integer.valueOf(2), deque.removeFront());
        assertEquals(Integer.valueOf(1), deque.removeFront());
        assertTrue(deque.isEmpty());
    }

    @Test
    public void addGetSizeTest() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(2);
        deque.addFirst(1);

        assertEquals("size: ", 2, deque.size());

        assertEquals("index 1: ", Integer.valueOf(2), deque.get(1));
    }

    @Test
    public void ExpandGetTest() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        for (int i = 5; i <= 10; ++i)
            deque.addLast(i);

        for (int i = 4; i >= 1; --i)
            deque.addFirst(i);

        assertEquals(10, deque.size());

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= 10; ++i) {
            if (i != 1)
                sb.append(", ");
            sb.append(deque.get(i - 1));
        }

        assertEquals("1, 2, 3, 4, 5, 6, 7, 8, 9, 10", sb.toString());
    }

    @Test
    public void RemoveShrinkTest() {
        ArrayDeque<Integer> deque = new ArrayDeque();

        for (int i = 1; i <= 10; i++)
            deque.addLast(i);

        for (int i = 6; i <= 10; ++i)
            deque.removeFirst();
        for (int i = 3; i >= 1; --i)
            deque.removeFront();

        assertEquals(2, deque.size());
        assertEquals("4, 5", deque.toString());
    }
}
