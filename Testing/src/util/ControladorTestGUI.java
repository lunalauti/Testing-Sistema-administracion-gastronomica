package util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Cerveceria;
import negocio.ControladorAdmin;
import vista.IVistaLogin;
import vista.VAdmin;

public class ControladorTestGUI implements ActionListener {

	private IVistaLogin vista = null;
	String tipo = null;
	private InterfazOptionPanel optionPane = new MiOptionPane();

	public void setVista(IVistaLogin vista, String tipo) {
		this.vista = vista;
		this.vista.setActionListener(this);
		this.tipo = tipo;
	}

	public IVistaLogin getVista() {
		return vista;
	}

	public InterfazOptionPanel getOptionPane() {
		return optionPane;
	}

	public void setOptionPane(InterfazOptionPanel optionPane) {
		this.optionPane = optionPane;
	}
	//

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();

		try {

			// AGREGADO DE NUEVAS COSAS
			if (comando.equalsIgnoreCase("ACEPTAR")) { // Agregar un nuevo operario, mozo, producto o promocion

				if (this.tipo.equalsIgnoreCase("RegistroProd")) {
					String nombre = this.vista.getNya();
					double pVenta = this.vista.pVenta();
					double pCosto = this.vista.pCosto();
					int stock = this.vista.stock();

					if (nombre.length() < 5)
						this.optionPane.ShowMessage(null, "El nombre del producto debe tener al menos 4 caracteres.");
					else if (pVenta < 0 || pCosto < 0 || stock < 0)
						this.optionPane.ShowMessage(null,
								"No se pueden dejar campos vacios ni ingresar valores negativos.");
					else if (pVenta < pCosto)
						this.optionPane.ShowMessage(null, "El precio de venta no puede ser menor al precio de costo.");
					else {
						Cerveceria.getInstance().getAdmin().agregarProducto(nombre, pCosto, pVenta, stock);
					}
				}
			}
		} catch (Exception exc) {
			this.optionPane.ShowMessage(null, exc.getMessage());
		}

	}

}