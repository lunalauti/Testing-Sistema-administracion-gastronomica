package modelo;

import java.util.ArrayList;

/**
 * * Clase PromocionProducto del modelo, se extiende de Promocion y representa a una promocion de determinado producto <br>
 * <b>Invariante: </b><br>
 * -id_promo,dtoPorCantidad_CantMinima y dtoPorCantidad_PrecioUnit deben ser mayor a 0<br>
 * -aplicaDosPorUno debe ser true si aplicaDtoPorCantidad es false y viceversa. <br>
 */
public class PromoProducto extends Promocion {
    private static int incremental = 0;
    public int id_promo;
    public Producto producto;
    public boolean aplicaDosPorUno;
    public boolean aplicaDtoPorCantidad;
    public int dtoPorCantidad_CantMinima;
    public double dtoPorCantidad_PrecioUnit;
    public boolean activa;

    /**
     * Crea una nueva instancia de una PromoProducto. <br>
     * <b>Pre:</b> diasDePromo debe ser distinto de null y no debe estar vacio.<br>
     * id_promo,dtoPorCantidad_CantMinima y dtoPorCantidad_PrecioUnit deben ser mayor a 0<br>
     * aplicaDosPorUno debe ser true si aplicaDtoPorCantidad es false y viceversa.<br>
     * producto no debe ser null<br>
     * <b>Post:</b> Nueva PromoProducto instanciada.<br>
     *
     * @param diasDePromo                : Es una coleccion con los dias en los que la promocion esta vigente
     * @param producto:                  Es una instancia de la clase Producto.
     * @param aplicaDosPorUno:           Determina si la promocion es 2x1 o no
     * @param aplicaDtoPorCantidad:      Determina si la promocion aplica comprando cierta cantidad del producto
     * @param dtoPorCantidad_CantMinima: Establece que cantidad hay que comprar para que aplique la promocion por cantidad
     * @param dtoPorCantidad_PrecioUnit: Establece el nuevo precio unitario al aplicar la promocion por cantidad
     * @param activa:                    Determina si la promo esta activa o no
     */
    public PromoProducto(ArrayList<String> diasDePromo, Producto producto, boolean aplicaDosPorUno, boolean aplicaDtoPorCantidad, int dtoPorCantidad_CantMinima, double dtoPorCantidad_PrecioUnit) {
        super(diasDePromo);
        this.id_promo = incremental++;
        this.aplicaDosPorUno = aplicaDosPorUno;
        this.aplicaDtoPorCantidad = aplicaDtoPorCantidad;
        this.dtoPorCantidad_CantMinima = dtoPorCantidad_CantMinima;
        this.dtoPorCantidad_PrecioUnit = dtoPorCantidad_PrecioUnit;
        this.producto = producto;
    }

    public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public void setId_promo(int id_promo) {
		this.id_promo = id_promo;
	}

	public int getId_promo() {
        return id_promo;
    }

    public boolean isAplicaDosPorUno() {
        return aplicaDosPorUno;
    }

    public void setAplicaDosPorUno(boolean aplicaDosPorUno) {
        this.aplicaDosPorUno = aplicaDosPorUno;
    }

    public boolean isAplicaDtoPorCantidad() {
        return aplicaDtoPorCantidad;
    }

    public void setAplicaDtoPorCantidad(boolean aplicaDtoPorCantidad) {
        this.aplicaDtoPorCantidad = aplicaDtoPorCantidad;
    }

    public int getDtoPorCantidad_CantMinima() {
        return dtoPorCantidad_CantMinima;
    }

    public void setDtoPorCantidad_CantMinima(int dtoPorCantidad_CantMinima) {
        this.dtoPorCantidad_CantMinima = dtoPorCantidad_CantMinima;
    }

    public double getDtoPorCantidad_PrecioUnit() {
        return dtoPorCantidad_PrecioUnit;
    }

    public void setDtoPorCantidad_PrecioUnit(double dtoPorCantidad_PrecioUnit) {
        this.dtoPorCantidad_PrecioUnit = dtoPorCantidad_PrecioUnit;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

	@Override
	public String toString() {
		return "id_promo=" + id_promo + ", producto=" + producto + ", aplicaDosPorUno=" + aplicaDosPorUno
				+ ", aplicaDtoPorCantidad=" + aplicaDtoPorCantidad + ", dtoPorCantidad_CantMinima="
				+ dtoPorCantidad_CantMinima + ", dtoPorCantidad_PrecioUnit=" + dtoPorCantidad_PrecioUnit + ", activa="
				+ activa;
	}
    
    
}
