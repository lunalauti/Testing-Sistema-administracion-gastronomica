package test;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import escenarios.EscenarioCerveceriaCargada;
import excepciones.*;
import modelo.*;

public class TestAsignarMesaMozo {
	
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
   	public void AsignarMesaLibreMozoNoDisp() { //MozoNoDisponibleException
   	 try {
   	
   		 Cerveceria cerveceria = escenario.getCerveceria();
   		 Mozo mozo2 = cerveceria.getMozos().get(1);
   		 Mesa mesa1 = cerveceria.getMesas().get(0);
   		 
   		 
   		 cerveceria.asignarMesa(mozo2,mesa1);
   		 Assert.fail("Debe largar excepcion mozo no disponible");
   		
   		} catch (MozoNoDisponibleException e) {
   			//test exitoso
   		} catch (MozoInexistenteException e) {
   			Assert.fail("Mozo existe");
   		} catch (MesaNoDisponibleException e) {
   			Assert.fail("Mesa disponible");
   		} catch (MesaInexistenteException e) {
   			Assert.fail("Mesa existe");
   		}
   	 
   	 }
    
	   
	   @Test  
	   	public void AsignarMesaNoDispMozoDisponible() {	//MesaNoDisponibleException
	   	 try {
	   	
	   		 Cerveceria cerveceria = escenario.getCerveceria();
	   		 Mozo mozo1 = cerveceria.getMozos().get(0); //juan lopez, activo
	   		 Mesa mesa4 = cerveceria.getMesas().get(1); //MESA N4 YA ASIGNADA

	   		 
	   		 cerveceria.asignarMesa(mozo1,mesa4);
	   		 cerveceria.asignarMesa(mozo1,mesa4);
	   		 Assert.fail("Debe largar excepcion mesa no disponible");
	   		
	   		} catch (MozoNoDisponibleException e) {
	   			Assert.fail("Mozo disponible");
	   		} catch (MozoInexistenteException e) {
	   			Assert.fail("Mozo existe");
	   		} catch (MesaNoDisponibleException e) {
	   			//test exitoso
	   		} catch (MesaInexistenteException e) {
	   			Assert.fail("Mesa existe");
	   		}
	   	 
	   	 }
	   
	   
	   @Test  
		public void AsignarMesaLibreMozoDisponible() {	//CASO CORRECTO
		 try {
		
			 Cerveceria cerveceria = escenario.getCerveceria();
			 System.out.println(cerveceria.getMozos());
			 System.out.println(cerveceria.getMesas());
			 Mozo mozo1 = cerveceria.getMozos().get(0); //Juan Lopez ACTIVO
			 Mesa mesa1 = cerveceria.getMesas().get(0); //MESA N1 SIN ASIGNAR
			 

			 cerveceria.asignarMesa(mozo1,mesa1);
			  if (!mozo1.getMesas().contains(mesa1))
				   Assert.fail("Error al asignar mesa");
			 
			} catch (MozoNoDisponibleException e) {
				Assert.fail("Mozo disponible");
			} catch (MozoInexistenteException e) {
				Assert.fail("Mozo existe");
			} catch (MesaNoDisponibleException e) {
				Assert.fail("Mesa disponible");
			} catch (MesaInexistenteException e) {
				Assert.fail("Mesa existe");
			}
		 
		 }
	   

	   
	   

}
