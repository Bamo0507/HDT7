package hdt6;

public class NodoArbol<E> {

    private String[] conjuntoPalabra = new String[2];
    private NodoArbol<E> izquierdo;
    private NodoArbol<E> derecho;

    public NodoArbol(String[] conjuntoPalabra) {
        this.conjuntoPalabra = conjuntoPalabra;
        izquierdo = null;
        derecho = null;
    }

    public String getConjuntoPalabra(int indice) {
        return conjuntoPalabra[indice];
    }

    public void setConjuntoPalabra(String[] conjuntoPalabra) {
        this.conjuntoPalabra = conjuntoPalabra;
    }

    public NodoArbol<E> getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoArbol<E> izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoArbol<E> getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoArbol<E> derecho) {
        this.derecho = derecho;
    }
}