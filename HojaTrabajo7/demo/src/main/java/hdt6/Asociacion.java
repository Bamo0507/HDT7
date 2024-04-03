package hdt6;

/**
 * Representa una asociación entre una clave y un valor.
 * @param <K> Tipo de la clave.
 * @param <V> Tipo del valor.
 */
public class Asociacion<K extends Comparable<K>, V> {

    private K key; // Clave de la asociación
    private V value; // Valor de la asociación

    /**
     * Constructor de la clase Asociacion.
     * @param key Clave de la asociación.
     * @param value Valor de la asociación.
     */
    public Asociacion(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Obtiene la clave de la asociación.
     * @return Clave de la asociación.
     */
    public K getKey() {
        return key;
    }

    /**
     * Obtiene el valor de la asociación.
     * @return Valor de la asociación.
     */
    public V getValue() {
        return value;
    }
}
