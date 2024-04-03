package hdt6;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class AppTest {

    private ArbolBinario<String> arbolBinario;

    @Before
    public void setUp() {
        arbolBinario = new ArbolBinario<>();
    }

    @Test
    public void testInsertarYBuscarAsociacionExistente() {
        // Insertar una asociación en el árbol
        arbolBinario.insertar(new String[]{"hello", "hola"});

        // Buscar la asociación en el árbol
        assertEquals("hola", arbolBinario.devolverEspanol("hello"));
    }

    @Test
    public void testBuscarAsociacionNoExistente() {
        // Buscar una asociación que no existe en el árbol
        assertEquals("*goodbye*", arbolBinario.devolverEspanol("goodbye"));
    }

    @Test
    public void testBuscarAsociacionExistente() {
        // Insertar asociaciones en el árbol
        arbolBinario.insertar(new String[]{"hello", "hola"});
        arbolBinario.insertar(new String[]{"world", "mundo"});

        // Buscar asociaciones en el árbol
        assertEquals("mundo", arbolBinario.devolverEspanol("world"));
    }
}
