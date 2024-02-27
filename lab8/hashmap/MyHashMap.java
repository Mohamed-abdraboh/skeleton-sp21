package hashmap;

import java.util.*;

/**
 * A hash table-backed Map implementation. Provides amortized constant time
 * access to elements via get(), remove(), and put() in the best case.
 * <p>
 * Assumes null keys will never be inserted, and does not resize down upon remove().
 *
 * @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private int initialCapacity = 16;
    private int count;
    private static double loadFactor = 0.75;

    /**
     * Constructors
     */
    public MyHashMap() {
        buckets = createTable(initialCapacity);
        count = 0;
    }

    public MyHashMap(int initialCapacity) {
        this.initialCapacity = initialCapacity;
        buckets = createTable(initialCapacity);
        count = 0;
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param maxLoad         maximum load factor
     */
    public MyHashMap(int initialCapacity, double maxLoad) {
        this(initialCapacity);
        loadFactor = maxLoad;
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     * <p>
     * The only requirements of a hash table bucket are that we can:
     * 1. Insert items (`add` method)
     * 2. Remove items (`remove` method)
     * 3. Iterate through items (`iterator` method)
     * <p>
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     * <p>
     * Override this method to use different data structures as
     * the underlying bucket type
     * <p>
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     * <p>
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return new Collection[tableSize];
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!


    @Override
    public void clear() {
        count = 0;
        Arrays.fill(buckets, null);
    }

    private int getHashCode(K key) {
        return Math.floorMod(key.hashCode(), initialCapacity);
    }

    @Override
    public boolean containsKey(K key) {
        Collection<Node> bucket = buckets[getHashCode(key)];
        if (bucket == null) return false;

        for (Node node : bucket)
            if (node.key.equals(key)) return true;
        return false;
    }

    @Override
    public V get(K key) {
        int keyIndex = getHashCode(key);
        Collection<Node> bucket = buckets[keyIndex];
        if (bucket == null) bucket = new LinkedList<>();

        for (Node node : bucket)
            if (node.key.equals(key)) return node.value;
        return null;
    }

    @Override
    public int size() {
        return count;
    }

    private void rehash() {
        Collection<Node>[] oldBuckets = buckets;
        initialCapacity *= 2;
        buckets = createTable(initialCapacity);

        count = 0;
        for (Collection<Node> bucket : oldBuckets)
            if (bucket != null) {
                for (Node node : bucket) {
                    put(node.key, node.value);
                }
            }
    }


    @Override
    public void put(K key, V value) {
        if ((double) (count / initialCapacity) > loadFactor) rehash();
        int keyIndex = getHashCode(key);

        if (containsKey(key)) {
            Collection<Node> bucket = buckets[keyIndex];
            for (Node node : bucket)
                if (node.key.equals(key))
                    node.value = value;

        } else {
            count++;

            if (buckets[keyIndex] == null)
                buckets[keyIndex] = createBucket();
            buckets[keyIndex].add(createNode(key, value));
        }


    }

    private Node getNodeFromBucket(Collection<Node> bucket, K key) {

        for (Node node : bucket)
            if (node.key.equals(key)) return node;

        return null;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        for (Collection<Node> bucket : buckets)
            if (bucket != null) {
                for (Node node : bucket)
                    keySet.add(node.key);
            }
        return keySet;
    }

    @Override
    public V remove(K key) {
        if (key == null)
            throw new NullPointerException();
        for (Collection<Node> bucket : buckets)
            if (bucket != null)
                for (Node node : bucket)
                    if (node.key.equals(key)) {
                        V result = node.value;
                        bucket.remove(node);
                        count--;
                        return result;
                    }
        return null;
    }

    @Override
    public V remove(K key, V value) {
        V toRemoveValue = get(key);
        if (toRemoveValue.equals(value)) {
            remove(key);
            return toRemoveValue;
        } else return null;
    }

    @Override
    public Iterator<K> iterator() {
        return new MyHashMapIterator<>(this);
    }
}
