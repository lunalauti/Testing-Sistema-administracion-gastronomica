package testGUIyPersistencia;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import excepciones.ProductoRepetidoException;
import modelo.Cerveceria;
import modelo.Producto;

public class TestPersistencia {

	Cerveceria cerveceria = Cerveceria.getInstance();
	EscenarioVacio escenario = new EscenarioVacio();

	/*
	 * Borra el archivo
	 */
	@Before
	public void setUp() {
		escenario.setUp();
		File arch = new File("Cerveceria.bin");
		if (arch.exists())
			arch.delete();
	}

	@After
	public void tearDown() {
		escenario.tearDown();
	}

	/*
	 * Verifica que el metodo persistir crea un archivo.
	 */
	@Test
	public void testCrearArchivo() {
		try {
			cerveceria.persistirCerveceria();
			File archivo = new File("Cerveceria.bin");
			Assert.assertTrue("Debería existir el archivo Cerveceria.bin", archivo.exists());
		} catch (IOException e) {
			fail("Error en la persistencia");
		}
	}

	/*
	 * Verifica la lectura y escritura de la cerveceria vacía.
	 */
	@Test
	public void testCerveceriaConArchivo() {
		try {
			cerveceria.persistirCerveceria();
			// Cerveceria.setInstance();
			Cerveceria cerveceria2 = Cerveceria.getInstance();
			cerveceria2.cargarCerveceria();
			Assert.assertEquals("Las cervecerias deberian estar vacias", this.cerveceria, cerveceria2);
		} catch (ClassNotFoundException e) {
			Assert.fail("No debería lanzar excepcion: " + e.getMessage());
		} catch (IOException e) {
			Assert.fail("No debería lanzar excepcion: " + e.getMessage());
		}
	}

	/*
	 * Verifica si se lanza una excepción al intentar leer un archivo inexistente.
	 */
	@Test
	public void testDespersitirSinArchivo() {
		try {
			cerveceria.cargarCerveceria();
			Assert.fail("Debería haber lanzado una excepcion");
		} catch (IOException | ClassNotFoundException e) {

		}
	}

	/*
	 * Verifica la lectura y escritura con datos
	 */
	@Test
	public void testConDatos() {
		try {
			EscenarioConDatos escenario2 = new EscenarioConDatos();
			// escenario2.setUp();
			cargarProductos(cerveceria);
			cerveceria.persistirCerveceria();
			Cerveceria cerveceria2 = Cerveceria.getInstance(); // No se si esta bien poner un getInstance();
			cerveceria2.cargarCerveceria();
			Assert.assertEquals("Las cervecerias deberian tener los mismos datos", cerveceria.getCarta(),
					cerveceria2.getCarta());
		} catch (IOException | ClassNotFoundException | ProductoRepetidoException e) {
			Assert.fail("No debería lanzar excepción: " + e.getMessage());
		}
	}

	private void cargarProductos(Cerveceria cerveceria) throws ProductoRepetidoException {
		cerveceria.agregarProducto(new Producto("Cerveza", 100, 125, 300));
		cerveceria.agregarProducto(new Producto("Gaseosa", 80, 100, 300));
		cerveceria.agregarProducto(new Producto("Pizza", 150, 250, 50));
	}

}
