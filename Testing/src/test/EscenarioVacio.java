package test;

import org.junit.After;
import org.junit.Before;

import modelo.Cerveceria;

public class EscenarioVacio {
	Cerveceria cerveceria;

	@Before
	public void setUp() {
		cerveceria = Cerveceria.getInstance();
		cerveceria.getMozos().clear();
		cerveceria.getMesas().clear();
		cerveceria.getOperarios().clear();
		cerveceria.getPromosProductos().clear();
		cerveceria.getPromosTemporales().clear();
		cerveceria.getComandasAbiertas().clear();
		cerveceria.getCarta().clear();
		cerveceria.getVentas().clear();
		cerveceria.setNombreLocal(null);
	}

	@After
	public void tearDown() {
		cerveceria = Cerveceria.getInstance();
		cerveceria.getMozos().clear();
		cerveceria.getMesas().clear();
		cerveceria.getOperarios().clear();
		cerveceria.getPromosProductos().clear();
		cerveceria.getPromosTemporales().clear();
		cerveceria.getComandasAbiertas().clear();
		cerveceria.getCarta().clear();
		cerveceria.getVentas().clear();
		cerveceria.setNombreLocal(null);
	}

}
