package modelo;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class Comanda {
	public Date fecha;
	public Mesa mesa;
	public ArrayList<Pedido> listaProductos = new ArrayList<Pedido>();
	public String estado; // abierta-cerrada

	/**
	 * Instancia una comanda. Se asocia a la mesa pasada por par�metro.<br>
	 * <b>Pre:</b> mesa != null. La mesa debe existir en el ArrayList de mesas de la
	 * cervecer�a.<br>
	 * listaProductos inicializada.<br>
	 * <b>Post:</b> Se intancia una comanda con estado = ABIERTA y tendr� referencia
	 * a la mesa de donde se tomo esa comanda.<br>
	 * 
	 * @param mesa : Mesa a la cual se le asignara la comanda.
	 */

	public Comanda(Mesa mesa, ArrayList<Pedido> listaProductos) {
		this.mesa = mesa;
		this.listaProductos = listaProductos;
		this.estado = "ABIERTA";
		this.fecha = Date.from(Instant.now());// bien?
	}

	public void actualizaListaProductos(ArrayList<Pedido> listaProductos) {
		for (Pedido pedido : listaProductos)
			this.listaProductos.add(pedido);
	}

	public void removePedido(Pedido pedido) {
		this.listaProductos.remove(pedido);
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void cerrarse() {
		this.setEstado("CERRADA");
	}

	public Date getFecha() {
		return fecha;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public ArrayList<Pedido> getListaProductos() {
		return listaProductos;
	}

	public String getEstado() {
		return estado;
	}
}
