package modelo;

import java.util.ArrayList;

/**
 * * Clase Promocion del modelo, es la clase padre de PromoProducto y
 * PromoTemporal <br>
 * <b>Invariante: </b><br>
 * -diasDePromo distintos de null y no puede estar vacio<br>
 */
public class Promocion {

	public ArrayList<String> diasDePromo = new ArrayList<String>(); // Lunes a Domingo
	private boolean activa;

	/**
	 * Crea una nueva instancia de una Promocion. <br>
	 * <b>Pre:</b> diasDePromo debe ser distinto de null y no debe estar vacio.<br>
	 * <b>Post:</b> Nueva promocion instanciada.<br>
	 * 
	 * @param diasDePromo : Es una coleccion con los dias en los que la promocion
	 *                    esta vigente
	 * @param activa:     Determina si la promo esta activa o no
	 */
	public Promocion(ArrayList<String> diasDePromo) {
		this.diasDePromo = diasDePromo;
		this.activa = true;
	}

	public ArrayList<String> getDiasDePromo() {
		return diasDePromo;
	}

	public void setDiasDePromo(ArrayList<String> diasDePromo) {
		this.diasDePromo = diasDePromo;
	}

	public boolean isActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}

	@Override
	public String toString() {
		return "diasDePromo=" + diasDePromo;
	}

}
