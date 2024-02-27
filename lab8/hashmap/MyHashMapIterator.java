package hashmap;

import java.util.Iterator;

public class MyHashMapIterator<K, V> implements Iterator<K> {

    private final Iterator<K> iterator;

    private int ptr;

    public MyHashMapIterator(MyHashMap<K, V> myHashMap) {
        this.iterator = myHashMap.keySet().iterator();
        ptr = 0;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public K next() {
        return iterator.next();
    }
}
