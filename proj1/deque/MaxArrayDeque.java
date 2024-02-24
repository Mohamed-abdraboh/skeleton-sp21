package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private final Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        this.comparator = c;
    }

    public T max() {
        return max(comparator);
    }

    public T max(Comparator<T> c) {
        if (isEmpty())
            return null;

        T maxItem = get(0);
        for (int i = 0; i < size(); ++i) {
            T item = get(i);
            if (c.compare(item, maxItem) > 0)
                maxItem = item;
        }
        return maxItem;
    }
}
