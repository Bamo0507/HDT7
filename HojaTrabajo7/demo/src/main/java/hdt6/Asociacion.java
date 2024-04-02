package hdt6;

public class Asociacion<K extends Comparable<K>, V> {

    private K key;
    private V value;

    public Asociacion(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}