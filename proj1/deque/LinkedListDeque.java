package deque;

public class LinkedListDeque<T> {

    public boolean isEmpty() {
        return size == 0;
    }


    private static class Node<T> {
        private final T data;
        private Node<T> next;
        private Node<T> prev;


        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }


    private Node<T> sentinel;
    private Node<T> last;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node<T>(null, null, null);
        last = sentinel;
        size = 0;
    }

    public LinkedListDeque(Node<T> head) {
        this();
        sentinel.next = head;
        sentinel.prev = head;
        last = head;
        size = 1;
    }

    public T removeFirst() {
        if (isEmpty())
            return null;
        T toReturn = sentinel.next.data;
        link(sentinel, sentinel.next.next);
        size--;

        return toReturn;
    }

    public T removeLast() {
        if (isEmpty())
            return null;
        T toReturn = last.data;
        last = last.prev;
        link(last, sentinel);
        size--;

        return toReturn;
    }

    public void addLast(T data) {
        Node<T> node = new Node<>(data, null, null);
        link(last, node);
        link(node, sentinel);
        last = node;
        size++;
    }

    private void link(Node<T> node1, Node<T> node2) {
        node1.next = node2;
        if (node2 != null)
            node2.prev = node1;
    }

    public int size() {
        return size;
    }

    public void addFirst(T data) {
        if (isEmpty()) {
            addLast(data);
            return;
        }
        Node<T> node = new Node<>(data, null, null);
        Node<T> head = sentinel.next;
        link(sentinel, node);
        link(node, head);
        size++;

    }

    public void printDeque() {
        Node<T> cur = sentinel;
        while (cur.next != null && cur.next != sentinel) {
            cur = cur.next;
            System.out.print(cur.next.data + " ");
        }
        System.out.println();
    }

}
