package hdt6;

// Importaciones estáticas para los métodos assertEquals y assertNull
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

// Importación de las anotaciones de JUnit
import org.junit.Before;
import org.junit.Test;

/**
 * Clase de pruebas unitarias para la clase ArbolBinario.
 */
public class AppTest {

    // Declaración de una instancia de ArbolBinario<String>
    private ArbolBinario<String> arbolBinario;

    // Método de inicialización que se ejecuta antes de cada prueba
    @Before
    public void setUp() {
        arbolBinario = new ArbolBinario<>(); // Inicializa el árbol binario
    }

    /**
     * Prueba la inserción y búsqueda de una asociación existente en el árbol.
     */
    @Test
    public void testInsertarYBuscarAsociacionExistente() {
        // Inserta una asociación en el árbol
        arbolBinario.insertar(new String[]{"hello", "hola"});

        // Busca la asociación en el árbol y verifica que sea la correcta
        assertEquals("hola", arbolBinario.devolverEspanol("hello"));
    }

    /**
     * Prueba la búsqueda de una asociación que no existe en el árbol.
     */
    @Test
    public void testBuscarAsociacionNoExistente() {
        // Busca una asociación que no existe en el árbol y verifica que retorne el valor esperado
        assertEquals("*goodbye*", arbolBinario.devolverEspanol("goodbye"));
    }

    /**
     * Prueba la búsqueda de una asociación existente en el árbol.
     */
    @Test
    public void testBuscarAsociacionExistente() {
        // Inserta algunas asociaciones en el árbol
        arbolBinario.insertar(new String[]{"hello", "hola"});
        arbolBinario.insertar(new String[]{"world", "mundo"});

        // Busca una asociación existente en el árbol y verifica que retorne el valor esperado
        assertEquals("mundo", arbolBinario.devolverEspanol("world"));
    }
}
