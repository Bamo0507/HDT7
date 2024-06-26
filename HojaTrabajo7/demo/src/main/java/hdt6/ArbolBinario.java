package hdt6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Representa un árbol binario para almacenar asociaciones de palabras.
 * @param <E> Tipo de los elementos del árbol.
 */
public class ArbolBinario<E extends Comparable<E>> {

    private NodoArbol<E> raiz; // Raíz del árbol

    /**
     * Inserta un conjunto de palabras en el árbol.
     * @param conjuntoPalabra Conjunto de palabras a insertar.
     */
    public void insertar(String[] conjuntoPalabra) {
        raiz = insertRec(raiz, conjuntoPalabra);
    }

    /**
     * Método auxiliar para la inserción recursiva de un conjunto de palabras en el árbol.
     * @param nodo Nodo actual en el que se está insertando.
     * @param conjuntoPalabra Conjunto de palabras a insertar.
     * @return Nodo actualizado después de la inserción.
     */
    private NodoArbol<E> insertRec(NodoArbol<E> nodo, String[] conjuntoPalabra) {
        if (nodo == null) {
            return new NodoArbol<>(conjuntoPalabra);
        }
        
        // Comparar la palabra con la palabra del nodo actual
        String nodoPalabra = nodo.getConjuntoPalabra(0);
        int comparacion = conjuntoPalabra[0].compareToIgnoreCase(nodoPalabra);
        
        // Insertar en el subárbol izquierdo si la palabra es menor
        if (comparacion < 0) {
            nodo.setIzquierdo(insertRec(nodo.getIzquierdo(), conjuntoPalabra));
        }
        // Insertar en el subárbol derecho si la palabra es mayor
        else if (comparacion > 0) {
            nodo.setDerecho(insertRec(nodo.getDerecho(), conjuntoPalabra));
        }
        // Si la palabra ya existe, actualizar su traducción
        else {
            nodo.setConjuntoPalabra(conjuntoPalabra);
        }
    
        return nodo;
    }
    

    /**
     * Devuelve la traducción al español de una palabra inglesa.
     * @param palabraIngles Palabra en inglés a traducir.
     * @return La traducción al español de la palabra inglesa, o la palabra entre asteriscos si no se encuentra.
     */
    public String devolverEspanol(String palabraIngles) {
        return devolverEspanolRec(raiz, palabraIngles);
    }

    /**
     * Método auxiliar para la búsqueda recursiva de la traducción al español de una palabra inglesa.
     * @param nodo Nodo actual en el que se está buscando.
     * @param palabraIngles Palabra en inglés a traducir.
     * @return La traducción al español de la palabra inglesa, o la palabra entre asteriscos si no se encuentra.
     */
    private String devolverEspanolRec(NodoArbol<E> nodo, String palabraIngles) {
        if (nodo == null) {
            // Verificar si la palabra inglesa es un signo de puntuación
            if (palabraIngles.equals(".") || palabraIngles.equals(",") || palabraIngles.equals(";")) {
                return palabraIngles; // Devolver el signo de puntuación sin modificar
            } else {
                return "*" + palabraIngles + "*"; // Devolver la palabra inglesa entre asteriscos si no se encuentra en el árbol
            }
        }
    
        // Convertir la palabra a minúsculas para una comparación insensible a mayúsculas y minúsculas
        String lowercasePalabraIngles = palabraIngles.toLowerCase();
    
        // Comparar la palabra inglesa con la palabra del nodo actual
        String nodoPalabra = nodo.getConjuntoPalabra(0);
        int comparacion = lowercasePalabraIngles.compareToIgnoreCase(nodoPalabra);
    
        // Si las palabras son iguales, devolver la traducción
        if (comparacion == 0) {
            return nodo.getConjuntoPalabra(1);
        }
        // Si la palabra inglesa es menor, buscar en el subárbol izquierdo
        else if (comparacion < 0) {
            return devolverEspanolRec(nodo.getIzquierdo(), palabraIngles);
        }
        // Si la palabra inglesa es mayor, buscar en el subárbol derecho
        else {
            return devolverEspanolRec(nodo.getDerecho(), palabraIngles);
        }
    }
    

    /**
     * Carga un diccionario desde un archivo en el árbol.
     * @param fileName Nombre del archivo que contiene el diccionario.
     */
    public void cargarDiccionario(String fileName) {
        ArrayList<String[]> listaPalabras = readWordsFromFile(fileName);
        for (String[] conjuntoPalabra : listaPalabras) {
            insertar(conjuntoPalabra);
        }
    }

    /**
     * Lee pares de palabras desde un archivo y las devuelve como una lista de arreglos.
     * @param fileName Nombre del archivo del que leer las palabras.
     * @return Lista de pares de palabras leídas del archivo.
     */
    private ArrayList<String[]> readWordsFromFile(String fileName) {
        ArrayList<String[]> wordPairsList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim(); // Eliminar espacios en blanco al principio y al final de la línea
                // Eliminar paréntesis al principio y al final de la línea
                if (line.startsWith("(")) {
                    line = line.substring(1);
                }
                if (line.endsWith(")")) {
                    line = line.substring(0, line.length() - 1);
                }

                String[] words = line.split(", "); // Dividir la línea en palabras
                if (words.length == 2) {
                    wordPairsList.add(words);
                } else {
                    System.out.println("Error: La línea no contiene dos palabras separadas por coma: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wordPairsList;
    }

    /**
     * Realiza un recorrido en orden del árbol e imprime las asociaciones palabra-traducción.
     */
    public void inOrderTraversal() {
        inOrderTraversal(raiz);
    }

    /**
     * Método auxiliar para realizar un recorrido en orden del árbol e imprimir las asociaciones palabra-traducción.
     * @param nodo Nodo actual en el que se está realizando el recorrido.
     */
    private void inOrderTraversal(NodoArbol<E> nodo) {
        if (nodo == null) {
            return;
        }

        Stack<NodoArbol<E>> stack = new Stack<>();
        NodoArbol<E> current = nodo;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.getIzquierdo();
            }

            current = stack.pop();
            System.out.println(current.getConjuntoPalabra(0) + ": " + current.getConjuntoPalabra(1));

            current = current.getDerecho();
        }
    }
}
