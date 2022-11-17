package test;

import static org.junit.Assert.*;

import java.time.DayOfWeek;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import excepciones.*;
import modelo.*;
import escenarios.EscenarioCerveceriaCargada;

public class TestEliminacion {

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
	public void EliminarOperarioExistente() {
		Cerveceria cerveceria = escenario.getCerveceria();
		
		Operario op = cerveceria.getOperarios().get(0);
		try {
			cerveceria.eliminarOperario(op);;
			if (cerveceria.getOperarios().contains(op))
				Assert.fail("No se elimino el operario");
		} catch (OperarioInexistenteException e) {
			Assert.fail("No deberia largar excepcion de inexistente.");
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void EliminarOperarioInexistente() {
		Cerveceria cerveceria = escenario.getCerveceria();
		Operario op = escenario.getOperario();
		try {
			cerveceria.eliminarOperario(op);
			Assert.fail("Debe largar excepcion OperarioInexistenteException");
		} catch (OperarioInexistenteException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void EliminarMesaExistente() {
		Cerveceria cerveceria = escenario.getCerveceria();
		Mesa mesa1 = cerveceria.getInstance().getMesas().get(0);
		
			try {
				cerveceria.eliminarMesa(mesa1);
				if (cerveceria.getMesas().contains(mesa1))
					Assert.fail("No se elimino correctamente la mesa del sistema");
			} catch (MesaInexistenteException e) {
				Assert.fail("No deberia largar esta excepcion");
				e.printStackTrace();
			}
			catch (MesaAsignadaException e) {
				Assert.fail("No deberia largar esta excepcion");
				e.printStackTrace();
			}
				
	}
	

	
	@Test
	public void EliminarMozoExistente() {
		Cerveceria cerveceria = escenario.getCerveceria();
		Mozo mozo1 = cerveceria.getInstance().getMozos().get(0);
		
			try {
				cerveceria.eliminarMozo(mozo1);
				if (cerveceria.getMozos().contains(mozo1))
					Assert.fail("No se elimino correctamente el mozo del sistema");
				
			} catch (MozoInexistenteException e) {
				Assert.fail("No deberia largar esta excepecion, el mozo existe");
				e.printStackTrace();
			}
				
	}
	
	@Test
	public void EliminarMozoInexistente() {
		Cerveceria cerveceria = escenario.getCerveceria();

		Mozo mozo = new Mozo("Juan Sanchez",2 , "18/03/1978");
		
		try {
			cerveceria.eliminarMozo(mozo);
			Assert.fail("Debe largar excepcion, mozo no existe");
		} catch (MozoInexistenteException e) {
			
			e.printStackTrace();
		}
				
	}
	
	@Test
	public void EliminarProductoExistente() {
		Cerveceria cerveceria = escenario.getCerveceria();

		Producto p1 = cerveceria.getCarta().get(0);
		
			
				try {
					cerveceria.eliminarProducto(p1);
					if (cerveceria.getCarta().contains(p1))
						Assert.fail("No se elimino correctamente el producto del sistema");
				}  
				catch (ProductoInexistenteException e) {
					Assert.fail("No deberia largar esta excepecion, el producto existe");
					e.printStackTrace();
				}
				
				
			
				
	}
	
	@Test
	public void EliminarProductoInexistente() {
		Cerveceria cerveceria = escenario.getCerveceria();
		Producto p1 = new Producto("No existe", 100,200,10);
		
		
			try {
				//cerveceria.deleteProducto(cerveceriaConDatos.getPancho());
				cerveceria.eliminarProducto(p1);
				Assert.fail("Debe largar ProductoInexistenteException");
				
			} 
			catch (ProductoInexistenteException e) {
				e.printStackTrace();
			}
			
		
				
	}
	
	@Test
	public void EliminarPromoProdExistente() {
		Cerveceria cerveceria = escenario.getCerveceria();

		PromoProducto p1 = cerveceria.getPromosProductos().get(0);
		try {
			cerveceria.eliminarPromocion(p1);
			if (cerveceria.getPromosProductos().contains(p1))
				Assert.fail("No se elimino promocion correctamente");
		} catch (PromocionInexistenteException e) {
			Assert.fail("No deberia largar excepcion, promo existe.");
			e.printStackTrace();
		}
		
		}
	
	@Test
	public void EliminarPromoProdInexistente() {
		Cerveceria cerveceria = escenario.getCerveceria();
		
		ArrayList<DayOfWeek> diasPromo = new ArrayList<DayOfWeek>();
		diasPromo.add(DayOfWeek.SUNDAY);
		
		PromoProducto promo = new PromoProducto(escenario.getFernet(),true,false,3,200.5,diasPromo);
			
		try {
			cerveceria.eliminarPromocion(promo);
			Assert.fail("Debe largar la excepcion");
		} catch (PromocionInexistenteException e) {
			
			e.printStackTrace();
		}
		
		}
	
	
	
	
	
	
	

}
