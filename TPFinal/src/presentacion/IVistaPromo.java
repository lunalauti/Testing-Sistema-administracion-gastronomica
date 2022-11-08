package presentacion;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import modelo.Producto;

public interface IVistaPromo {
	void cerrarse();

	void setActionListener(ActionListener actionListener);

	String getTipoPromo();

	boolean getDosPorUno();

	String getNombre();

	String getFormaPago();

	int getPorcentaje();

	boolean getAcumulable();

	Producto getProducto();

	int getCant();

	double getPrecio();
	
	ArrayList<String> getDias();
	
	void cargaProductos(ArrayList<Producto> productos);

}
