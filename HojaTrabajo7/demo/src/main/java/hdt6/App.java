package hdt6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class App {

    private static ArbolBinario<String> arbolBinario = new ArbolBinario<>();

    /**
     * Método principal de la aplicación.
     * Carga el diccionario en el árbol binario, traduce palabras y muestra el resultado final.
     * @param args Argumentos de la línea de comandos (no utilizados).
     * @throws Exception Si ocurre algún error durante la ejecución.
     */
    public static void main(String[] args) throws Exception {
        // Se carga el diccionario en el árbol binario
        arbolBinario.cargarDiccionario("diccionario.txt");

        // Imprimir las relaciones de las palabras
        System.out.println("---------------------------------------------");
        System.out.println("Relaciones de las palabras en inglés:");
        arbolBinario.inOrderTraversal();

        // Se cargan las palabras a traducir
        ArrayList<String> palabrasTraducir = readWordsFromFile("texto.txt");
        System.out.println("---------------------------------------------");
        System.out.println("Las palabras a traducir son: \n");
        for (String p : palabrasTraducir) {
            System.out.println(p);
        }
        System.out.println("---------------------------------------------");

        // Se traducen las palabras
        System.out.println("Traducción: \n");
        ArrayList<String> palabrasTraducidas = traducirPalabras(palabrasTraducir, arbolBinario);
        for (String p : palabrasTraducidas) {
            System.out.println(p);
        }

        // Se imprime el resultado final
        System.out.println("---------------------------------------------");
        System.out.println("Resultado Final: \n");
        String resultadoFinal = concatenarPalabras(palabrasTraducidas);
        System.out.println(resultadoFinal);
        System.out.println("---------------------------------------------");
    }

    /**
     * Lee palabras de un archivo y las devuelve como una lista.
     * @param fileName Nombre del archivo del que leer las palabras.
     * @return Lista de palabras leídas del archivo.
     */
    public static ArrayList<String> readWordsFromFile(String fileName) {
        ArrayList<String> wordsList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Utilizar expresiones regulares para dividir la línea en palabras
                String[] words = line.split("\\s+|(?=\\p{Punct})|(?<=\\p{Punct})");
                // Agregar cada palabra a la lista de palabras
                wordsList.addAll(Arrays.asList(words));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordsList;
    }

    /**
     * Traduce una lista de palabras utilizando el árbol binario de traducción.
     * @param palabrasTraducir Lista de palabras a traducir.
     * @param arbol Árbol binario de traducción.
     * @return Lista de palabras traducidas.
     */
    public static ArrayList<String> traducirPalabras(ArrayList<String> palabrasTraducir, ArbolBinario<String> arbol) {
        ArrayList<String> palabrasTraducidas = new ArrayList<>();
        for (String palabra : palabrasTraducir) {
            palabrasTraducidas.add(arbol.devolverEspanol(palabra));
        }
        return palabrasTraducidas;
    }

    /**
     * Concatena una lista de palabras, manteniendo la puntuación adecuada.
     * @param palabrasTraducidas Lista de palabras a concatenar.
     * @return Resultado de la concatenación de palabras.
     */
    public static String concatenarPalabras(ArrayList<String> palabrasTraducidas) {
        StringBuilder result = new StringBuilder();

        for (String p : palabrasTraducidas) {
            // Si la palabra actual comienza con uno de los símbolos de puntuación
            if (p.startsWith(",") || p.startsWith(".") || p.startsWith(";")) {
                // Eliminar la última palabra agregada al resultado
                if (result.length() > 0 && result.charAt(result.length() - 1) == ' ') {
                    result.deleteCharAt(result.length() - 1);
                }
                // Concatenar el símbolo de puntuación con la palabra anterior
                result.append(p);
            } else {
                // Si la palabra anterior no termina con un espacio, agregar un espacio antes de la palabra actual
                if (result.length() > 0 && result.charAt(result.length() - 1) != ' ') {
                    result.append(" ");
                }
                // Concatenar la palabra actual
                result.append(p);
            }
        }
        return result.toString();
    }
}
