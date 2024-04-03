package hdt6;

/**
 * Representa un nodo en un árbol binario.
 * @param <E> Tipo del elemento almacenado en el nodo.
 */
public class NodoArbol<E> {

    private String[] conjuntoPalabra = new String[2]; // Conjunto de palabras almacenadas en el nodo
    private NodoArbol<E> izquierdo; // Referencia al hijo izquierdo del nodo
    private NodoArbol<E> derecho; // Referencia al hijo derecho del nodo

    /**
     * Constructor de la clase NodoArbol.
     * @param conjuntoPalabra Conjunto de palabras a almacenar en el nodo.
     */
    public NodoArbol(String[] conjuntoPalabra) {
        this.conjuntoPalabra = conjuntoPalabra;
        izquierdo = null;
        derecho = null;
    }

    /**
     * Obtiene una palabra del conjunto almacenado en el nodo.
     * @param indice Índice de la palabra a obtener (0 para la palabra en inglés, 1 para la palabra en español).
     * @return La palabra solicitada.
     */
    public String getConjuntoPalabra(int indice) {
        return conjuntoPalabra[indice];
    }

    /**
     * Establece el conjunto de palabras almacenadas en el nodo.
     * @param conjuntoPalabra Conjunto de palabras a almacenar en el nodo.
     */
    public void setConjuntoPalabra(String[] conjuntoPalabra) {
        this.conjuntoPalabra = conjuntoPalabra;
    }

    /**
     * Obtiene la referencia al hijo izquierdo del nodo.
     * @return La referencia al hijo izquierdo del nodo.
     */
    public NodoArbol<E> getIzquierdo() {
        return izquierdo;
    }

    /**
     * Establece la referencia al hijo izquierdo del nodo.
     * @param izquierdo Referencia al nuevo hijo izquierdo del nodo.
     */
    public void setIzquierdo(NodoArbol<E> izquierdo) {
        this.izquierdo = izquierdo;
    }

    /**
     * Obtiene la referencia al hijo derecho del nodo.
     * @return La referencia al hijo derecho del nodo.
     */
    public NodoArbol<E> getDerecho() {
        return derecho;
    }

    /**
     * Establece la referencia al hijo derecho del nodo.
     * @param derecho Referencia al nuevo hijo derecho del nodo.
     */
    public void setDerecho(NodoArbol<E> derecho) {
        this.derecho = derecho;
    }
}
