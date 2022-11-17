package testCajaNegra;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import escenarios.EscenarioCerveceriaCargada;
import excepciones.*;
import modelo.*;


public class TestAgregarProducto {

	EscenarioCerveceriaCargada escenario;
	
	@Before
	public void setUp() throws Exception {
		escenario = new EscenarioCerveceriaCargada();
	}

	@After
	public void tearDown() throws Exception {
		Cerveceria.setInstance();
	}

	
	@Test
	public void AgregarProductoInexistente() {
		Cerveceria cerveceria = escenario.getCerveceria();
		Producto p1 = new Producto("milanesa", 1000, 1210, 30);
		
		try {
			cerveceria.agregarProducto(p1);
			if (!cerveceria.getCarta().contains(p1))
				Assert.fail("No se ingreso correctamente el producto al sistema");
		} catch (ProductoRepetidoException e) {
		
		}
		
		
	}
	
	@Test
	public void AgregarProductoExistente() {
		Cerveceria cerveceria = escenario.getCerveceria();
		Producto hamburguesa = new Producto("hamburguesa", 400, 1000, 10);
		
		try {
			cerveceria.agregarProducto(hamburguesa);
			Assert.fail("Deberia lanzarse excepcion de producto repetido Exception");
		} catch (ProductoRepetidoException e) {
			
		}
	}

}
