package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;

import modelo.Cerveceria;
import modelo.Mesa;
import modelo.Mozo;
import modelo.Operario;
import modelo.Producto;
import modelo.PromoProducto;
import modelo.PromoTemporal;
import negocio.Sistema;
import presentacion.IVistaAdmin;
import presentacion.VAdmin;
import presentacion.VLogin;
import presentacion.VMesa;
import presentacion.VMozo;
import presentacion.VProducto;
import presentacion.VPromocion;
import presentacion.VRegistroOp;

public class ControladorAdmin implements ActionListener {

	private IVistaAdmin vista = null;
	private static ControladorAdmin instance = null;

	private ControladorAdmin() {
	}

	public static ControladorAdmin getInstance() {
		if (instance == null)
			instance = new ControladorAdmin();
		return instance;
	}

	public IVistaAdmin getVista() {
		return vista;
	}

	public void setVista(IVistaAdmin vista) {
		this.vista = vista;
		this.vista.setActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if (comando.equalsIgnoreCase("REGISTRAR_MOZO")) {
			String nya = this.vista.getNombre();
			int cantHijos = this.vista.getCant();
			Date fechaNac = this.vista.getFecha();
			Sistema.getInstance().addMozo(nya, fechaNac, cantHijos);
		} else if (comando.equalsIgnoreCase("REGISTRAR_OPERARIO")) {
			String nya = this.vista.getNombre();
			String user = this.vista.getUsername();
			String pass = this.vista.getPassword();
			Sistema.getInstance().addOperario(nya, user, pass);
		} else if (comando.equalsIgnoreCase("REGISTRAR_PRODUCTO")) {
			String nombre = this.vista.getNombre();
			double pCosto = this.vista.getPCosto();
			double pVenta = this.vista.getPVenta();
			int stock = this.vista.getCant();
			Sistema.getInstance().addProducto(nombre, pCosto, pVenta, stock);
		} else if (comando.equalsIgnoreCase("REGISTRAR_MESA")) {
			try {
				int cantComensales = this.vista.getCant();
				int nroMesa = this.vista.getNroComensales();
				Sistema.getInstance().addMesa(cantComensales, nroMesa);
			} catch (Exception exc) {
				JOptionPane.showMessageDialog(null, "Se debe ingresar un caracter numercio");
			}
		} else if (comando.equalsIgnoreCase("SALIR_ADMIN")) {
			this.vista.cerrarse();
			ControladorLogin.getInstance().setVista(new VLogin());
		} else if (comando.equalsIgnoreCase("OPERARIO")) {
			this.vista.cerrarse();
			this.setVista(new VRegistroOp());
		} else if (comando.equalsIgnoreCase("PRODUCTO")) {
			this.vista.cerrarse();
			this.setVista(new VProducto());
		} else if (comando.equalsIgnoreCase("MESA")) {
			this.vista.cerrarse();
			this.setVista(new VMesa());
		} else if (comando.equalsIgnoreCase("PROMOCION")) {
			this.vista.cerrarse();
			ControladorPromo.getInstance().setVista(new VPromocion());
			ControladorPromo.getInstance().getVista().cargaProductos(Cerveceria.getInstance().getProductos());
		} else if (comando.equalsIgnoreCase("MOZO")) {
			this.vista.cerrarse();
			this.setVista(new VMozo());
		} else if (comando.equalsIgnoreCase("ELIMINAR_MOZO")) {
			Mozo mozo = this.vista.getSelectedMozo();
			Sistema.getInstance().deleteMozo(mozo);
		} else if (comando.equalsIgnoreCase("ELIMINAR_OP")) {
			Operario op = this.vista.getSelectedOperario();
			Sistema.getInstance().deleteOperario(op);
		} else if (comando.equalsIgnoreCase("ELIMINAR_MESA")) {
			Mesa mesa = this.vista.getSelectedMesa();
			Sistema.getInstance().deleteMesa(mesa);
		} else if (comando.equalsIgnoreCase("ELIMINAR_PRODUCTO")) {
			Producto producto = this.vista.getSelectedProducto();
			Sistema.getInstance().deleteProducto(producto);
		} else if (comando.equalsIgnoreCase("ELIMINAR_PROMOT")) {
			PromoTemporal promo = this.vista.getSelectedPromoTemp();
			Sistema.getInstance().deletePromoTemp(promo);
		} else if (comando.equalsIgnoreCase("ELIMINAR_PROMOP")) {
			PromoProducto promo = this.vista.getSelectedPromoProd();
			Sistema.getInstance().deletePromoProducto(promo);
		} else if (comando.equalsIgnoreCase("SALIR")) {
			ControladorAdmin.getInstance().getVista().cerrarse();
			ControladorAdmin.getInstance().setVista(new VAdmin());
			Sistema.getInstance().actualizarListaAdmin();
		}
	}

}
