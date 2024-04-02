package hdt6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class ArbolBinario<E extends Comparable<E>> {

    private NodoArbol<E> raiz;

    public void insertar(String[] conjuntoPalabra) {
        raiz = insertRec(raiz, conjuntoPalabra);
    }

    private NodoArbol<E> insertRec(NodoArbol<E> nodo, String[] conjuntoPalabra) {
        if (nodo == null) {
            return new NodoArbol<>(conjuntoPalabra);
        }
        
        // Insertar recursivamente en el subárbol izquierdo si está vacío
        if (nodo.getIzquierdo() == null) {
            nodo.setIzquierdo(new NodoArbol<E>(conjuntoPalabra));
        } 
        // Insertar recursivamente en el subárbol derecho si está vacío
        else if (nodo.getDerecho() == null) {
            nodo.setDerecho(new NodoArbol<E>(conjuntoPalabra));
        } 
        // Si ambos subárboles no están vacíos, insertar en el subárbol izquierdo del hijo izquierdo
        else {
            insertRec(nodo.getIzquierdo(), conjuntoPalabra);
        }
    
        return nodo;
    }
    

    public String devolverEspanol(String palabraIngles) {
        return devolverEspanolRec(raiz, palabraIngles);
    }

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
        
        if (nodo.getConjuntoPalabra(0).equalsIgnoreCase(lowercasePalabraIngles)) {
            return nodo.getConjuntoPalabra(1);
        }
    
        String resultadoIzquierdo = devolverEspanolRec(nodo.getIzquierdo(), palabraIngles);
        String resultadoDerecho = devolverEspanolRec(nodo.getDerecho(), palabraIngles);
    
        return (resultadoIzquierdo != null) ? resultadoIzquierdo : resultadoDerecho;
    }

    public void cargarDiccionario(String fileName) {
        ArrayList<String[]> listaPalabras = readWordsFromFile(fileName);
        for (String[] conjuntoPalabra : listaPalabras) {
            insertar(conjuntoPalabra);
        }
    }

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

    public void inOrderTraversal() {
        inOrderTraversal(raiz);
    }

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