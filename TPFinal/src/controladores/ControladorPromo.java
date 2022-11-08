package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import modelo.Producto;
import negocio.Sistema;
import presentacion.IVistaPromo;

public class ControladorPromo implements ActionListener {

	private IVistaPromo vista = null;
	private static ControladorPromo instance = null;

	private ControladorPromo() {
	}

	public static ControladorPromo getInstance() {
		if (instance == null)
			instance = new ControladorPromo();
		return instance;
	}

	public IVistaPromo getVista() {
		return vista;
	}

	public void setVista(IVistaPromo vista) {
		this.vista = vista;
		this.vista.setActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if (comando.equalsIgnoreCase("ENVIAR")) {
			ArrayList<String> dias = this.vista.getDias();
			if (vista.getTipoPromo().equalsIgnoreCase("TEMPORAL")) {
				String nombre = this.vista.getNombre();
				String formaPago = this.vista.getFormaPago();
				int porcentaje = this.vista.getPorcentaje();
				boolean acumulable = this.vista.getAcumulable();
				Sistema.getInstance().addPromoTemporal(dias, nombre, formaPago, porcentaje, acumulable);
			} else {
				Producto producto = this.vista.getProducto();
				int cant = this.vista.getCant();
				double precio = this.vista.getPrecio();
				boolean dosPorUno= this.vista.getDosPorUno();
				Sistema.getInstance().addPromoProducto(dias,producto,dosPorUno,!dosPorUno,cant,precio);
			}
		}
	}

}
