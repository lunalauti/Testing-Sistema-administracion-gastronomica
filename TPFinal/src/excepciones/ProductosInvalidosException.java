package excepciones;

import modelo.Operario;

public class ProductosInvalidosException extends Exception {
	
	public ProductosInvalidosException(String mensaje) {
		super(mensaje);
	}
}
