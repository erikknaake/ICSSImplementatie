package nl.han.ica.icss.typesystem;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ScopedMap<K, V> implements IScopedMap<K, V> {
    private LinkedList<Map<K, V>> scopedMap;

    public ScopedMap() {
        scopedMap = new LinkedList<>();
        pushScope();
    }

    public void clear() {
        scopedMap.clear();
        pushScope();
    }

    public void pushScope() {
        scopedMap.addFirst(new HashMap<>());
    }

    public void popScope() {
        scopedMap.removeFirst();
    }

    public void put(K key, V value) {
        scopedMap.getFirst().put(key, value);
    }

    public V get(K key) {
        for (Map<K, V> map : scopedMap) {
            V currentValue = map.get(key);
            if (currentValue != null)
                return currentValue;
        }
        return null;
    }
}
