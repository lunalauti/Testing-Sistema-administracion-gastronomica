package testGUIyPersistencia;

import static org.junit.Assert.fail;

import java.time.DayOfWeek;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;

import modelo.Cerveceria;
import modelo.FormaPago;
import modelo.Operario;
import modelo.Pedido;
import modelo.Producto;

public class EscenarioConDatos {

	Cerveceria cerveceria;

	@Before
	public void setUp() {
		Cerveceria.setInstance();
		
		try {
			cerveceria = Cerveceria.getInstance();
			cerveceria.agregarOperario(new Operario("Santiago Sosa", "santiso", "Santi1234"));
			cerveceria.agregarOperario(new Operario("Lautaro Luna", "lautiluna", "Lauti1234"));
			cerveceria.agregarMesa(5);
			cerveceria.agregarMesa(5);
			cerveceria.agregarMesa(5);
			cerveceria.agregarMozo("Julian Alvarez", 3, "17/08/2000");
			cerveceria.agregarMozo("Martina Diaz", 0, "20/01/1998");
			cerveceria.agregarMozo("Lucas Rodriguez", 1, "14/05/2001");
			cerveceria.agregarProducto(new Producto("Cerveza", 100, 125, 300));
			cerveceria.agregarProducto(new Producto("Gaseosa", 80, 100, 300));
			cerveceria.agregarProducto(new Producto("Pizza", 150, 250, 50));
			ArrayList<DayOfWeek> dias = new ArrayList<DayOfWeek>();
			dias.add(DayOfWeek.WEDNESDAY);
			dias.add(DayOfWeek.MONDAY);
			cerveceria.agregarPromocion("Pizza", false, true, 3, 200, dias);
			dias.clear();
			dias.add(DayOfWeek.THURSDAY);
			dias.add(DayOfWeek.FRIDAY);
			cerveceria.agregarPromocion("Cerveza", true, false, 0, 0, dias);
			dias.add(DayOfWeek.SUNDAY);
			dias.add(DayOfWeek.MONDAY);
			dias.add(DayOfWeek.TUESDAY);
			dias.add(DayOfWeek.WEDNESDAY);
			dias.add(DayOfWeek.SATURDAY);
			cerveceria.agregarPromocion("Happy Hour", FormaPago.EFECTIVO, 0.15, false, 18, 20, dias);
			dias.clear();
			dias.add(DayOfWeek.THURSDAY);
			cerveceria.agregarPromocion("Cuenta DNI", FormaPago.CTADNI, 0.3, true, 9, 23, dias);
			
		} catch (Exception e) {
			fail("No debería lanzar excepción");
		}

	}

	@After
	public void tearDown() {
	}

}
