package escenarios;

import java.time.DayOfWeek;
import java.util.ArrayList;

import modelo.Cerveceria;
import modelo.EstadoMozo;
import modelo.FormaPago;
import modelo.Mesa;
import modelo.Operario;
import modelo.Producto;

public class EscenarioCerveceriaCargada {
	
	Producto fernet =  new Producto("Fernet", 400, 1000, 20);
	Operario op = new Operario("Felipe", "Felipe Juarez", "Felipe123");
	
	Cerveceria cerveceria = Cerveceria.getInstance();
	
	public EscenarioCerveceriaCargada() {
		try {

			//se cargan mesas
			cerveceria.agregarMesa(3); //MESA N1
			cerveceria.agregarMesa(4); //MESA 4
			cerveceria.getMesas().get(1).setAsignada(true); //pongo la mesa N4 como asignada
			
			Operario op1 = new Operario("Jose Gonzalez", "JOSEGONZALEZ", "Jose123");
			cerveceria.agregarOperario(op1);
			
			//se cargan mozos
			cerveceria.agregarMozo("Juan Lopez", 2, "15/01/2000");
			cerveceria.agregarMozo("Jose Perez", 1, "15/01/1986");
			cerveceria.getMozos().get(1).setEstado(EstadoMozo.AUSENTE);
			cerveceria.agregarMozo("Ramiro ", 1, "15/01/1986");
			cerveceria.agregarMozo("Julian", 1, "15/01/1986");


			
			//se cargan los productos
			Producto hamburguesa = new Producto("hamburguesa", 400, 1000, 10);
			cerveceria.agregarProducto(hamburguesa);
			Producto cerveza = new Producto("cerveza", 200, 400, 50);
			cerveceria.agregarProducto(cerveza);
			Producto papasfritas = new Producto("papas fritas", 200, 400, 50);
			cerveceria.agregarProducto(papasfritas);
			
			//se agrega una promocion de producto

			
			//creo los dias de promo producto
			ArrayList<DayOfWeek> diasPromo = new ArrayList<DayOfWeek>();
			diasPromo.add(DayOfWeek.MONDAY);
			
			//se carga la promo producto
			cerveceria.agregarPromocion("hamburguesa", false, true, 3, 200,diasPromo);
			cerveceria.agregarPromocion("cerveza", false, true, 5, 100,diasPromo);
			
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	public Cerveceria getCerveceria() {
		return cerveceria;
	}
	
	public Producto getFernet() {
		return fernet;
	}
	
	public Operario getOperario() {
		return op;
	}
	
}