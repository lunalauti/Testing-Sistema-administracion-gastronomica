package modelo;

import excepciones.UsuarioRepetidoException;

public class Admin extends Operario {

	public boolean editado=false;
	/**
	 * Crea una nueva instancia de la clase Admin. <br>
	 * <b>Post:</b> El Admin se crear� con un nombre de usuario (ADMIN) y una
	 * contrase�a (ADMIN1234) por defecto.<br>
	 */
	public Admin() {
		super("ADMINISTRADOR", "ADMIN", "ADMIN1234");
	}

	/**
	 * Cambia el nombre de usuario y la contrase�a del Admin <br>
	 * <b>Pre:</b> username != null y el nuevo username debe ser diferente al
	 * previo.<br>
	 * password !=null y la nueva password debe ser diferente a la �ltima
	 * establecida.<br>
	 * <b>Post:</b> El Admin tendr� un nuevo username y contrase�a con los valores
	 * pasados por par�metro.<br>
	 * 
	 * @param username  : Nuevo nombre de usuario del Admin.
	 * @param password: Nueva contrase�a del Admin.
	 */

//    public void cambiarUserPass(String username, String password) throws PasswordYaUtilizadaException,UsernameYaUtilizadoException { //no sabia que nombre ponerle
//        
//    	if (super.getUsername().equals(username))
//    		throw new UsernameYaUtilizadoException();
//    	if (super.getPassword().equals(password))
//    		throw new PasswordYaUtilizadaException();
//    	
//    	super.setUsername(username);
//        super.setPassword(password);
//    }

	/**
	 * Agrega un operario a la lista de operarios de la cerveceria con los datos
	 * pasados por par�metro<br>
	 * <b>Pre:</b> nya !=null. username!=null. password!=null. La lista de operarios
	 * debe estar inicializada.<br>
	 * <b>Post:</b> El operario debe ser instanciado y agregado a la lista de
	 * operarios de la cerveceria.<br>
	 *
	 * @param nya      : Nombre y apellido del operario.
	 * @param username : Nombre de usuario que utilizar� el operario para acceder al
	 *                 sistema luego de ser registrado.
	 * @param password : Contrase�a que utilizar� el operario para acceder al
	 *                 sistema luego de ser registrado.
	 * @throws OperarioRepetidoException : Se lanza si el operario ya existe en el
	 *                                   ArrayList de operarios.
	 */
	public void addOperario(String nya, String username, String password) throws UsuarioRepetidoException {

		int i = 0;

		while (i < Cerveceria.getInstance().getOperarios().size()
				&& !Cerveceria.getInstance().getOperarios().get(i).getUsername().equals(username)) {
			i++;
		}

		if (i < Cerveceria.getInstance().getOperarios().size()) { // salgo antes si encontre un username igual
			throw new UsuarioRepetidoException(username);
		}

		Operario op = new Operario(nya, username, password);
		Cerveceria.getInstance().addOperario(op); // la cerveceria verifica si ya existe

	}

	public void addMozo(Mozo mozo) {
		Cerveceria.getInstance().addMozo(mozo);
	}

	public void addProducto(Producto producto) {
		Cerveceria.getInstance().addProducto(producto);
	}

	public void addMesa(int cantComensales, int nroMesa) {

		Cerveceria.getInstance().addMesa(new Mesa(cantComensales, nroMesa));
	}

	public void addPromoProducto(PromoProducto promocion) {
		Cerveceria.getInstance().addPromoProd(promocion);
	}

	public void addPromoTemporal(PromoTemporal promocion) {
		Cerveceria.getInstance().addPromoTemp(promocion);
	}

	public void deleteMozo(Mozo mozo) {
		Cerveceria.getInstance().deleteMozo(mozo);
	}

	public void deleteMesa(Mesa mesa) {
		Cerveceria.getInstance().deleteMesa(mesa);
	}

	public void deleteProducto(Producto producto) {
		Cerveceria.getInstance().deleteProducto(producto);
	}

	public void deleteOperario(Operario operario) {
		Cerveceria.getInstance().deleteOperario(operario);
	}

	public void deletePromoTemporal(PromoTemporal promo) {
		Cerveceria.getInstance().deletePromoTemporal(promo);
	}

	public void deletePromoProducto(PromoProducto promo) {
		Cerveceria.getInstance().deletePromoProducto(promo);
	}
}
