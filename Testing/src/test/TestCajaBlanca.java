package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import excepciones.OperarioInexistenteException;
import excepciones.OperarioRepetidoException;
import junit.framework.Assert;
import modelo.Cerveceria;
import modelo.Operario;

//metodo void loguear(String username, String password, String tipo)
@SuppressWarnings("deprecation")
public class TestCajaBlanca {

	Cerveceria cerveceria;
	String username;
	String password;
	String tipo;

	@Before
	public void setUp() {
		cerveceria = Cerveceria.getInstance();
	}

	@After
	public void tearDown() {
		cerveceria.getOperarios().clear();
		cerveceria.setOperarioLogueado(null);
	}

	/*
	 * (1-3) - 4 - 8 - 9 - 10 - 9 - 11 - 12 - 16 - 17 - 18 - 21 - 22 - 23
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testCamino1() {
		try {
			cerveceria.getOperarios().clear();
			cerveceria.agregarOperario(new Operario("Usuario1", "user1", "User1234"));
			cerveceria.agregarOperario(new Operario("Usuario2", "user2", "User456"));
			this.username = "user2";
			this.password = "User456";
			this.tipo = "OPERARIO";

			cerveceria.loguear(username, password, tipo);
			Assert.assertEquals("user2", cerveceria.getOperarioLogueado().getUsername());
		} catch (OperarioRepetidoException e) {
			// No deberia lanzarse la excepcion
		}
	}

	/*
	 * (1-3) - 4 - 8 - 9 - 11 - 14 - 16 - 22 - 23
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testCamino2() {
		try {
			cerveceria.getOperarios().clear();
			Operario op = new Operario("Usuario1", "user1", "User1234");
			cerveceria.agregarOperario(op);
			cerveceria.agregarOperario(new Operario("Usuario2", "user2", "User456"));
			cerveceria.setEstadoOperario(op, false);

			this.username = "user1";
			this.password = "User1234";
			this.tipo = "OPERARIO";

			cerveceria.loguear(username, password, tipo);
			Assert.assertEquals(null, cerveceria.getOperarioLogueado());
		} catch (OperarioRepetidoException | OperarioInexistenteException e) {
			// No deberia lanzarse la excepcion
		}
	}

	/*
	 * (1-3) - 4 - 8 - 15 - 16 - 22 - 23
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testCamino3() {
		cerveceria.setOperarioLogueado(null);

		this.username = "user1";
		this.password = "User1234";
		this.tipo = "OTRO";

		cerveceria.loguear(username, password, tipo);
		Assert.assertEquals(null, cerveceria.getOperarioLogueado());
	}

	/*
	 * (1-3) - 4 - 5 - 6 - 15 - 16 - 17- 20 - 21 - 22 - 23
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testCamino4() {
		this.username = "ADMIN";
		this.password = "ADMIN1234";
		this.tipo = "ADMIN";

		cerveceria.loguear(username, password, tipo);
		Assert.assertEquals("ADMIN", cerveceria.getOperarioLogueado().getUsername());
	}

	/*
	 * (1-3) - 4 - 5 -15- 16 - 22 - 23
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testCamino5() {
		this.username = "admin";
		this.password = "ADMIN12345";
		this.tipo = "ADMIN";
		
		cerveceria.loguear(username, password, tipo);
		Assert.assertEquals(null, cerveceria.getOperarioLogueado());
	}
}
