package excepciones;
import modelo.Pedido;

public class CantInsuficienteProdException extends Exception {

	public CantInsuficienteProdException(String mensaje) {
		super(mensaje);
	}
}
