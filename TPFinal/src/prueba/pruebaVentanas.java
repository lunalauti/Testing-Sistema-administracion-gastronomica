package prueba;

import controlador.ControladorLogin;
import modelo.Cerveceria;
import modelo.Mesa;
import modelo.Operario;
import modelo.Producto;

public class pruebaVentanas {
	public static void main(String[] args) {

		Cerveceria cerveceria = Cerveceria.getInstance();
		// cerveceria.addOperario(new Operario("Juan", "Juancito", "Juan123456"));
		cerveceria.addMesa(new Mesa(10));
		cerveceria.addMesa(new Mesa(2));
		cerveceria.addProducto(new Producto("Hamburguesa", 150, 300, 20));
		cerveceria.addProducto(new Producto("Milanesa", 200.50, 450, 15));
		ControladorLogin login = ControladorLogin.getInstance();
		/*
		 * ControladorAdmin admin= ControladorAdmin.getInstance(); admin.setVista(new
		 * VAdmin());
		 */
	}

}
