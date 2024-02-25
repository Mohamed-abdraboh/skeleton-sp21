package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class BSTNode {
        K key;
        V value;
        BSTNode left;
        BSTNode right;

        public BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }

        public BSTNode(K key, V value, BSTNode left, BSTNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }


    }

    private BSTNode root;
    private int size;

    public BSTMap() {
        root = null;
        size = 0;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return getKey(root, key) != null;
    }


    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(BSTNode node, K key) {
        if (node == null)
            return null;
        if (key.compareTo(node.key) < 0)
            return get(node.left, key);
        else if (key.compareTo(node.key) > 0)
            return get(node.right, key);
        else return node.value;
    }

    private K getKey(BSTNode node, K key) {
        if (node == null)
            return null;
        if (key.compareTo(node.key) < 0)
            return getKey(node.left, key);
        else if (key.compareTo(node.key) > 0)
            return getKey(node.right, key);
        else return node.key;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        BSTNode node = put(root, key, value);
        if (root == null)
            root = node;
        size++;
        System.out.println();
    }

    private BSTNode put(BSTNode node, K key, V value) {
        if (node == null)
            return new BSTNode(key, value);

        if (key.compareTo(node.key) < 0)
            node.left = put(node.left, key, value);
        else if (key.compareTo(node.key) > 0)
            node.right = put(node.right, key, value);

        return node;
    }

    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(BSTNode node) {
        if (node == null)
            return;
        System.out.print(node.value);
        printInOrder(node.left);
        printInOrder(node);
        printInOrder(node.right);
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();

    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();

    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}