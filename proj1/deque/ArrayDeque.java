package deque;

public class ArrayDeque<T> {
    private T[] arr;
    private static final int FACTOR = 2;
    private static final double MIN_CAPACITY_PERCENTAGE = 0.25;
    private int startIndex;
    private int size;


    public ArrayDeque() {
        int capacity = 8;
        arr = (T[]) new Object[capacity];
        startIndex = 0;
        size = 0;
    }


    public T get(int index) {
        return arr[(index + startIndex) % arr.length];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void expand() {
        resize(arr.length * FACTOR);
    }

    private void shrink() {
        resize(arr.length / FACTOR);
    }

    private void resize(int capaicty) {
        T[] expandedArr = (T[]) new Object[capaicty];
        for (int i = 0; i < size; ++i)
            expandedArr[i] = get(i);
        arr = expandedArr;
        startIndex = 0;
    }

    public void printDeque() {
        for (int i = 0; i < size; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }


    public void addFirst(T value) {
        if (size == arr.length)
            expand();
        startIndex = (startIndex - 1 + arr.length) % arr.length;
        arr[startIndex] = value;
        size++;
    }

    public void addLast(T value) {
        if (size == arr.length)
            expand();

        int index = (startIndex + size) % arr.length;
        arr[index] = value;
        size++;
    }


    public T removeFirst() {
        if (size < MIN_CAPACITY_PERCENTAGE * arr.length)
            shrink();

        int index = (startIndex + size - 1) % arr.length;
        T removedValue = arr[index];
        arr[index] = null;
        size--;
        return removedValue;
    }

    public T removeFront() {
        if (size < MIN_CAPACITY_PERCENTAGE * arr.length)
            shrink();

        T removedValue = arr[startIndex];
        arr[startIndex] = null;
        startIndex++;
        size--;
        return removedValue;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; ++i) {
            if (i != 0)
                sb.append(", ");
            sb.append(get(i));
        }
        return sb.toString();
    }
}
