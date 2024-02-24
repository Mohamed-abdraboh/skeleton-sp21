package bstmap;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private static class BSTNode<K extends Comparable<K>> {
        K data;
        BSTNode left;
        BSTNode right;

        BSTNode(K data, BSTNode left, BSTNode right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        boolean containsData(K key) {
            if (this.data == key)
                return true;

            else if (this.data.compareTo(key) < 0)
                right.containsData(key);
            else
                left.containsData(key);

            return false;
        }
    }

    private BSTNode<K> head;
    private int size;

    public BSTMap() {
        size = 0;
    }

    public BSTMap(BSTNode head) {
        this.head = head;
        this.size = 1;
    }

    public void printInOrder() {

    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return head.containsData(key);

    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void put(K key, V value) {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public V remove(K key, V value) {
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }


}
