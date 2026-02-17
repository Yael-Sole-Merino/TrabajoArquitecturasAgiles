import org.junit.jupiter.api.;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.;

class PrincipalAppTest {

    private ArrayList<Caja> supermercado;
    private final InputStream systemIn = System.in;

    @BeforeEach
    void setUp() {
        supermercado = new ArrayList<>();
    }

    // Método auxiliar para simular la entrada del usuario
    void proporcionarEntrada(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
        // Es necesario reinicializar el scanner de la clase original 
        // porque ya estaba creado con el System.in antiguo
        PrincipalApp.sc = new Scanner(System.in);
    }

    @AfterEach
    void restaurarSystemIn() {
        System.setIn(systemIn);
    }

    @Test
    @DisplayName("Debería crear una caja correctamente")
    void testCrearCaja() {
        proporcionarEntrada("Caja1\n100\n");
        PrincipalApp.crearCaja(supermercado);

        assertEquals(1, supermercado.size());
        assertEquals("Caja1", supermercado.get(0).getId());
        assertEquals(100, supermercado.get(0).getSaldo());
    }
@Test
    @DisplayName("Debería eliminar una caja existente")
    void testEliminarCaja() {
        supermercado.add(new Caja("Borrar", 50));
        proporcionarEntrada("0\n"); // Seleccionamos el índice 0

        PrincipalApp.eliminarCaja(supermercado);

        assertTrue(supermercado.isEmpty());
    }

    @Test
    @DisplayName("Debería ingresar dinero en una caja específica")
    void testIngresarDinero() {
        supermercado.add(new Caja("Caja1", 100));
        proporcionarEntrada("0\n50\n"); // Índice 0, ingresar 50

        PrincipalApp.ingresarDinero(supermercado);

        assertEquals(150, supermercado.get(0).getSaldo());
    }

    @Test
    @DisplayName("Debería retirar dinero si hay saldo suficiente")
    void testRetirarDineroExitoso() {
        supermercado.add(new Caja("Caja1", 100));
        proporcionarEntrada("0\n40\n"); // Índice 0, retirar 40

        PrincipalApp.retirarDinero(supermercado);

        assertEquals(60, supermercado.get(0).getSaldo());
    }

    @Test
    @DisplayName("Debería transferir dinero entre dos cajas")
    void testTransferirDinero() {
        supermercado.add(new Caja("Origen", 100));
        supermercado.add(new Caja("Destino", 50));
        // 0 (origen), 1 (destino), 30 (cantidad)
        proporcionarEntrada("0\n1\n30\n");

        PrincipalApp.transferirDinero(supermercado);

        assertEquals(70, supermercado.get(0).getSaldo());
        assertEquals(80, supermercado.get(1).getSaldo());
    }

    @Test
    @DisplayName("Debería retornar true al llamar a salirPrograma")
    void testSalirPrograma() {
        assertTrue(PrincipalApp.salirPrograma());
    }
@Test
    @DisplayName("Debería devolver el índice seleccionado por el usuario")
    void testSeleccionarCajasSupermercado() {
        supermercado.add(new Caja("Caja0", 0));
        supermercado.add(new Caja("Caja1", 0));
        proporcionarEntrada("1\n");

        int seleccion = PrincipalApp.seleccionarCajasSupermercado(supermercado);

        assertEquals(1, seleccion);
    }
}