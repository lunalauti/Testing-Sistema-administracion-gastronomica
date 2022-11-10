package modelo;

import java.io.Serializable;

/**
 * Clase Mesa <br>
 * <b>Invariante: </b><br>
 * - nroMesa>=0 - cantComensales>0. - estado !=null y estado = "LIBRE" o estado
 * = "OCUPADA".
 */

public class Mesa implements Serializable {
	public int nroMesa;
	public int cantComensales;
	public String estado; // libre-ocupada
	public boolean asignado;

	/**
	 * Crea una nueva instancia de una mesa. <br>
	 * <b>Pre:</b> cantComensales > 0.nroMesa >=0<br>
	 * <b>Post:</b> Se instancia una nueva mesa con estado "LIBRE", sin asignar, con
	 * un numero de mesa y cantComensales pasados por parametro.<br>
	 * 
	 * @param cantComensales: cantidad de sillas que posee la mesa.
	 * @param nroMesa: numero de mesa de la mesa a instanciar.
	 */
	public Mesa(int cantComensales, int nroMesa) {
		this.estado = "LIBRE";
		this.asignado = false;
		this.cantComensales = cantComensales;
		this.nroMesa = nroMesa;
		this.invariante();

	}

	public boolean isAsignado() {
		return asignado;
	}

	public void setAsignado(boolean asignado) {
		this.asignado = asignado;
		this.invariante();
	}

	public void setCantComensales(int cantComensales) {
		this.cantComensales = cantComensales;
		this.invariante();
	}

	public void setEstado(String estado) {
		this.estado = estado;
		this.invariante();
	}

	public int getNroMesa() {
		return nroMesa;
	}

	public int getCantComensales() {
		return cantComensales;
	}

	public String getEstado() {
		return estado;
	}

	public void cerrarse() { // es necesario?
		this.estado = "LIBRE";
		this.invariante();
	}

	public void invariante() {
		assert this.cantComensales > 0 : "la cantidad de comensales debe ser > 0";
		assert this.nroMesa >= 0 : "El numero de mesa debe ser >=0";
		assert this.estado != null && (estado.equalsIgnoreCase("libre") || this.estado.equalsIgnoreCase("ocupada"))
				: "El estado de la mesa debe ser LIBRE o OCUPADA";
	}

	@Override
	public String toString() {
		return "#" + nroMesa + ", cantComensales=" + cantComensales + ", estado=" + estado + ", asignado=" + asignado;
	}

}
