package modelo;

import java.util.ArrayList;
import java.util.Iterator;

import excepciones.MesaInexistenteException;
import excepciones.MesaNoDisponibleException;
import excepciones.MozoInexistenteException;
import excepciones.MozoNoDisponibleException;
import excepciones.ProductosInvalidosException;
import excepciones.UsuarioRepetidoException;

public class Cerveceria {
	private static Cerveceria instance = null;
	private String nombreLocal;
	private double sueldo;
	private Admin admin;
	private ArrayList<Mozo> mozos = new ArrayList<Mozo>();
	private ArrayList<Mesa> mesas = new ArrayList<Mesa>();
	private ArrayList<Producto> productos = new ArrayList<Producto>();
	private ArrayList<Operario> operarios = new ArrayList<Operario>();
	private ArrayList<Comanda> comandasAbiertas = new ArrayList<Comanda>();
	private ArrayList<Venta> ventas = new ArrayList<Venta>();
	private ArrayList<PromoTemporal> promoTemporales = new ArrayList<PromoTemporal>();
	private ArrayList<PromoProducto> promosProducto = new ArrayList<PromoProducto>();

	private Cerveceria() {
		this.admin = new Admin();
	}

	public static Cerveceria getInstance() {
		if (instance == null)
			instance = new Cerveceria();
		return instance;
	}

	/**
	 * Modifica el estado del mozo pasado por par�metro (por el estado e).<br>
	 * <b>Pre:</b> mozo != null. e != null y debe ser ACTIVO, FRANCO o AUSENTE.<br>
	 * <b>Post:</b> El mozo pasado por par�metro tendr� como nuevo estado el pasado
	 * por par�metro.<br>
	 *
	 * @param mozo : mozo al cual se quiere cambiar su estado.
	 * @param e    : nuevo estado que se le asignar� al mozo.
	 * @throws MozoInexsitenteException : Se lanza si el mozo pasado por par�metro
	 *                                  no existe en el ArrayList de mozos de la
	 *                                  cervecer�a.
	 * 
	 */

	public void setEstado(Mozo mozo, Estado e) throws MozoInexistenteException {
		int i = 0;
		while (i < mozos.size() && !mozo.equals(mozos.get(i)))
			i++;
		if (i < mozos.size() && mozo.equals(mozos.get(i)))
			mozo.setEstado(e);
		else
			throw new MozoInexistenteException("El mozo seleccionado no existe");

		this.invariante();
	}

	/**
	 * Asigna al mozo pasado por par�metro la mesa pasada por par�metro<br>
	 * <b>Pre:</b> mozo != null. mesa != null<br>
	 * <b>Post:</b> La mesa se agrega al ArrayList de mesas del mozo. El estado de
	 * la mesa pasa a "OCUPADA"<br>
	 * 
	 * @param mozo : El mozo al cual se quiere agregar una mesa.
	 * @param mesa : Numero de mesa a asignarle.
	 * 
	 * @throws MozoNoDisponibleException : Se lanza si el mozo pasado por par�metro
	 *                                   no est� disponible.
	 * @throws MozoInexsitenteException  : Se lanza si el mozo pasado por par�metro
	 *                                   no existe en el ArrayList de mozos de la
	 *                                   cerveceria.
	 * @throws MesaNoDisponibleException : Se lanza si la mesa pasada por par�metro
	 *                                   no est� disponible.
	 * @throws MesaInexsitenteException  : Se lanza si la mesa pasada por par�metro
	 *                                   no existe en el ArrayList de mesas de la
	 *                                   cerveceria.
	 */
	public void asignarMesa(Mozo mozo, Mesa mesa) throws MozoNoDisponibleException, MozoInexistenteException,
			MesaNoDisponibleException, MesaInexistenteException {

		int i = 0;

		while (i < mozos.size() && !mozo.equals(mozos.get(i)))
			i++;

		if (i < mozos.size() && mozo.equals(mozos.get(i))) { // existe el mozo

			if (mozo.getEstado() == Estado.ACTIVO) { // el mozo est� disponible
				i = 0;

				while (i < mesas.size() && !mesa.equals(mesas.get(i)))
					i++;

				if (i < mesas.size() && mesa.equals(mesas.get(i))) { // la mesa existe

					if (mesa.getEstado().equalsIgnoreCase("LIBRE")) { // el mozo y la mesa est�n disponibles
						mozo.addMesa(mesa);
						mesa.setAsignado(true);
						mesa.setEstado("OCUPADA");
					} else
						throw new MesaNoDisponibleException("La mesa " + mesa.getNroMesa() + " no esta disponible");
				} else
					throw new MesaInexistenteException("La mesa seleccionada no existe");
			} else
				throw new MozoNoDisponibleException("El mozo " + mozo.getNya() + " no esta disponible");
		} else
			throw new MozoInexistenteException("El mozo seleccionado no existe");

		this.invariante();
	}

	/**
	 * Inicializa el total de ventas y la cantidad de ventas de cada mozo de la
	 * cervecer�a en 0<br>
	 * <b>Pre:La lista de mozos debe estar inicializada.</b> <br>
	 * <b>Post:</b> Cada mozo del ArrayList de mozos tiene ventas totales = 0 y cant
	 * ventas = 0<br>
	 */
	public void reiniciarMozos() {
		for (Mozo mozoAct : this.mozos)
			mozoAct.reinicio();
	}

	public void cerrarMesa(Mesa mesa, String formaPago) {
		int pos;
		boolean temporal = false, enc;
		ArrayList<Promocion> listaPromos = new ArrayList<Promocion>();
		PromoTemporal promoTemp;
		PromoProducto promoProducto;
		double total = 0;

		pos = getComanda(mesa);
		if (pos != -1) {
			Comanda comandaAct = this.comandasAbiertas.get(pos);

			/*
			 * Busca si hay una promo temporal activa
			 */
			Iterator<PromoTemporal> iteratorPromoTemp = this.promoTemporales.iterator();
			while (iteratorPromoTemp.hasNext() && !temporal) {
				promoTemp = (PromoTemporal) iteratorPromoTemp.next();
				if (promoTemp.getDiasDePromo().contains(Util.intToDia(comandaAct.getFecha().getDay()))
						&& promoTemp.isActiva())
					if (promoTemp.getFormaPago().equals(formaPago)) {
						temporal = true;
						listaPromos.add(promoTemp);
					}
			}

			/*
			 * Busca por cada producto la promo que le corresponda
			 */
			for (Pedido pedido : comandaAct.listaProductos) {
				Iterator<PromoProducto> iteratorPromoProducto = this.promosProducto.iterator();
				enc = false;
				while (iteratorPromoProducto.hasNext() && !enc) {
					promoProducto = (PromoProducto) iteratorPromoProducto.next();
					if ((pedido.getProducto().equals(promoProducto.getProducto()))
							&& promoProducto.getDiasDePromo().contains(Util.intToDia(comandaAct.getFecha().getDay()))
							&& promoProducto.isActiva()) {
						enc = true;
						listaPromos.add(promoProducto);
					}
				}
				if (enc) {
					promoProducto = (PromoProducto) listaPromos.get(listaPromos.size() - 1);
					if (temporal) {
						promoTemp = (PromoTemporal) listaPromos.get(0);
						if (promoTemp.isEsAcumulable())
							total += getPrecioConDescuento(pedido, promoProducto) * promoTemp.getPorcentajeDesc() / 100;
						else
							total += getPrecioConDescuento(pedido, promoProducto);
					} else
						total += getPrecioConDescuento(pedido, promoProducto);
				} else if (temporal) {
					promoTemp = (PromoTemporal) listaPromos.get(0);
					total += (pedido.getProducto().getpVenta() * pedido.getCantidad()) * promoTemp.getPorcentajeDesc()
							/ 100;
				} else
					total += pedido.getProducto().getpVenta() * pedido.getCantidad();
			}

			comandaAct.setEstado("CERRADA");
			mesa.setEstado("LIBRE");
			this.ventas.add(new Venta(comandaAct, total, formaPago, listaPromos));
			this.comandasAbiertas.remove(pos);

		} else
			System.out.println("LA MESA NO TIENE COMANDAS ABIERTAS");

	}

	/**
	 * Retorna el precio con descuento del pedido pasado por par�metro considerando
	 * la promo del producto del producto de ese pedido (y la cantidad pedida)<br>
	 * <b>Pre:pedido != null. promoProducto !=null y promoProducto debe estar
	 * ACTIVA. Adem�s debe coincidir el d�a de promoci�n de promoProducto con el del
	 * producto del pedido.</b> <br>
	 * <b>Post:</b> Devolver� el precio con Descuento del pedido considerando el
	 * producto, la cantidad y el descuento que se le aplica.<br>
	 * 
	 * @param pedido        : pedido al cual se le aplicar� la promoci�n.
	 * @param promoProducto : promoci�n del producto que se le aplicar� al pedido.
	 * 
	 */

	private double getPrecioConDescuento(Pedido pedido, PromoProducto promoProducto) {
		double precio = pedido.getProducto().getpVenta() * pedido.getCantidad();
		if (promoProducto.isAplicaDosPorUno())
			precio -= pedido.getProducto().getpVenta() * Math.floorDiv((int)pedido.getCantidad(), 2);
		else if (pedido.getCantidad() >= promoProducto.getDtoPorCantidad_CantMinima()) // si no cumple con la cant min,
																						// no aplico desc
			precio = promoProducto.getDtoPorCantidad_PrecioUnit() * pedido.getCantidad();

		return precio;
	}

	/* Si la mesa tiene una comanda, devuelve el index de la comanda, sino -1 */

	public int getComanda(Mesa mesa) {
		int i = 0;
		boolean enc = false;
		while (i < this.comandasAbiertas.size() && enc == false) {
			if (mesa.equals(this.comandasAbiertas.get(i).mesa))
				enc = true;
			else
				i++;
		}
		return (enc == false) ? -1 : i;
	}

	/**
	 * Toma una nueva comanda en caso de que la mesa este libre. Si la mesa pasada
	 * por par�metro est� ocupada, se agregan los pedidos al ArrayList de pedidos de
	 * la comanda abierta de esa mesa.<br>
	 * <b>Pre:</b> mozo != null. mesa != null<br>
	 * <b>Post:</b> La mesa se agrega al ArrayList de mesas del mozo. El estado de
	 * la mesa pasa a "OCUPADA"<br>
	 *
	 * @param mozo : El mozo al cual se quiere agregar una mesa.
	 * @param mesa : Numero de mesa a asignarle.
	 */

	public void tomarComanda(Mesa mesa, ArrayList<Pedido> pedidos)
			throws MesaInexistenteException, ProductosInvalidosException {

		int i = 0;
		ArrayList<Pedido> pedidosValidos = new ArrayList<Pedido>();
		;

		while (i < mesas.size() && !mesa.equals(mesas.get(i)))
			i++;

		if (i < mesas.size() && mesa.equals(mesas.get(i))) { // la mesa existe

			// verifico que al menos 1 producto de los pedidos del ArrayList exista (en el
			// ArrayList de Productos)
			for (Pedido pedido : pedidos) {
				if (existeProducto(pedido.getProducto())) {
					pedidosValidos.add(pedido);
				}
			}
			if (pedidosValidos.size() <= 0) // No se pidi� ning�n producto v�lido
				throw new ProductosInvalidosException("No se pidi� ning�n producto v�lido");

			int pos = getComanda(mesa);

			if (pos != -1) // la mesa ya tiene una comanda abierta
				agregarPedido(this.comandasAbiertas.get(pos), pedidosValidos);
			else // la mesa esta libre
				addComanda(new Comanda(mesa, pedidosValidos));
		} else
			throw new MesaInexistenteException("la mesa elegida no existe");

	}

	public boolean existeProducto(Producto producto) {

		for (Producto producto_i : productos) { // recorro el ArrayList de productos de la cervecer�a
			if (producto_i.equals(producto))
				return true; // el producto existe
		}
		return false;
	}

	private void agregarPedido(Comanda comanda, ArrayList<Pedido> pedidos) {
		comanda.actualizaListaProductos(pedidos);
	}

	/**
	 * Agrega el operario pasado por par�metro al ArrayList de operarios.<br>
	 * <b>Pre:</b> op != null<br>
	 * <b>Post:</b> El operario pasado por par�metro se agreg� al ArrayList de
	 * operarios.<br>
	 *
	 * @param operario : Operario que se busca agregar a la cervecer�a.
	 * @throws UsuarioRepetidoException : Se lanza si el operario ya hay un operario
	 *                                  registrado con el mismo username.
	 */

	public void addOperario(Operario op) throws UsuarioRepetidoException {

		for (Operario opAct : this.operarios) {
			if (opAct.getUsername().equals(op.getUsername()))
				throw new UsuarioRepetidoException(
						"No se pudo registrar al operario " + op.getUsername() + ". Nombre de usuario repetido.");
		}
		this.operarios.add(op);
	}

	public void invariante() {

	}

	// Metodos necesarios para el LOGIN

	public Operario getOperario(String username) {
		int i = 0;
		Operario op = null;

		while (i < this.operarios.size() && !this.operarios.get(i).getUsername().equalsIgnoreCase(username))
			i++;

		if (i < this.operarios.size())
			op = this.operarios.get(i);

		return op;
	}

	public void addMozo(Mozo mozo) {
		this.mozos.add(mozo);
	}

	public void addProducto(Producto producto) {
		this.productos.add(producto);
	}

	public void addMesa(Mesa mesa) {
		this.mesas.add(mesa);
	}

	public void addComanda(Comanda comanda) {
		this.comandasAbiertas.add(comanda);
	}

	public void addVenta(Venta venta) {
		this.ventas.add(venta);
	}

	public void addPromoTemp(PromoTemporal promo) {
		this.promoTemporales.add(promo);
	}

	public void addPromoProd(PromoProducto promo) {
		this.promosProducto.add(promo);
	}

	public void deleteOperario(Operario op) {
		this.operarios.remove(op);
	}

	public void deleteMozo(Mozo mozo) {
		this.mozos.remove(mozo);
	}

	public void deleteProducto(Producto prod) {
		this.productos.remove(prod);
	}

	public void deleteMesa(Mesa mesa) {
		this.mesas.remove(mesa);
	}

	public void deleteComanda(Comanda comanda) {
		this.comandasAbiertas.remove(comanda);
	}

	public void deletePromoTemporal(PromoTemporal promo) {
		this.promoTemporales.remove(promo);
	}

	public void deletePromoProducto(PromoProducto promo) {
		this.promosProducto.remove(promo);
	}

	// getters

	public Admin getAdmin() {
		return admin;
	}

	public String getNombreLocal() {
		return nombreLocal;
	}

	public double getSueldo() {
		return sueldo;
	}

	public ArrayList<Mozo> getMozos() {
		return mozos;
	}

	public ArrayList<Mesa> getMesas() {
		return mesas;
	}

	public ArrayList<Producto> getProductos() {
		return productos;
	}

	public ArrayList<Operario> getOperarios() {
		return operarios;
	}

	public ArrayList<Comanda> getComandasAbiertas() {
		return comandasAbiertas;
	}

	public ArrayList<Venta> getVentas() {
		return ventas;
	}

	public ArrayList<PromoTemporal> getPromoTemporales() {
		return promoTemporales;
	}

	public ArrayList<PromoProducto> getPromosProducto() {
		return promosProducto;
	}

	public void setNombreLocal(String nombreLocal) {
		this.nombreLocal = nombreLocal;
	}

	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}

	public void setMozos(ArrayList<Mozo> mozos) {
		this.mozos = mozos;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public void setMesas(ArrayList<Mesa> mesas) {
		this.mesas = mesas;
	}

	public void setProductos(ArrayList<Producto> productos) {
		this.productos = productos;
	}

	public void setOperarios(ArrayList<Operario> operarios) {
		this.operarios = operarios;
	}

	public void setComandasAbiertas(ArrayList<Comanda> comandasAbiertas) {
		this.comandasAbiertas = comandasAbiertas;
	}

	public void setVentas(ArrayList<Venta> ventas) {
		this.ventas = ventas;
	}

	public void setPromoTemporales(ArrayList<PromoTemporal> promoTemporales) {
		this.promoTemporales = promoTemporales;
	}

	public void setPromosProducto(ArrayList<PromoProducto> promosProducto) {
		this.promosProducto = promosProducto;
	}
}
