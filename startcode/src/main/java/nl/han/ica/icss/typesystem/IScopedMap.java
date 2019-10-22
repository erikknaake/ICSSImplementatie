package nl.han.ica.icss.typesystem;

public interface IScopedMap<K, V> {
    void clear();

    void pushScope();

    void popScope();

    void put(K key, V value);

    V get(K key);
}
