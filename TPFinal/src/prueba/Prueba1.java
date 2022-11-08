package prueba;

import java.util.ArrayList;
import java.util.Date;

import modelo.Cerveceria;
import modelo.Comanda;
import modelo.Mesa;
import modelo.Mozo;
import modelo.Operario;
import modelo.Pedido;
import modelo.Producto;
import modelo.PromoProducto;
import modelo.PromoTemporal;

public class Prueba1 {

	public static void main(String[] args) {

		try {
			Cerveceria cerveceria1 = Cerveceria.getInstance(); // � no se le pasa ningun param al crearla?
			Operario op1 = new Operario("Santi Sosa", "SANTISOSA", "123");
			cerveceria1.addOperario(op1);
			System.out.println(cerveceria1.getOperarios().get(0).getUsername());
			Date fecha1 = new Date();

			Mesa mesa1 = new Mesa(4);
			cerveceria1.addMesa(mesa1);// esta bien solo pasarle la cant de comensales??
			Mesa mesa2 = new Mesa(6);
			cerveceria1.addMesa(mesa2);
			Mesa mesa3 = new Mesa(2);
			cerveceria1.addMesa(mesa3);

			Mozo mozo1 = new Mozo("Santi Lapi", fecha1, 2);
			cerveceria1.addMozo(mozo1);
			Mozo mozo2 = new Mozo("Wencho Avalos", fecha1, 1);
			cerveceria1.addMozo(mozo2);
			Mozo mozo3 = new Mozo("Jose Gonzalez", fecha1, 3);
			cerveceria1.addMozo(mozo3);

			cerveceria1.asignarMesa(mozo1, mesa1);
			cerveceria1.asignarMesa(mozo2, mesa2);
			cerveceria1.asignarMesa(mozo3, mesa3);

			Producto milanesa = new Producto("milanesa", 500, 800, 10);
			cerveceria1.addProducto(milanesa);
			Producto hamburguesa = new Producto("hamburguesa", 400, 1000, 10);
			cerveceria1.addProducto(hamburguesa);
			Producto cerveza = new Producto("cerveza", 200, 400, 50);
			cerveceria1.addProducto(cerveza);

			Pedido pedido1 = new Pedido(hamburguesa, 2); // 2 hamburguesas
			Pedido pedido2 = new Pedido(cerveza, 2); // 2 cervezas
			Pedido pedido3 = new Pedido(milanesa, 5);

			// YA ESTAN ASIGNADOS LOS MOZOS A LAS MESAS Y EXISTEN LOS PRODUCTOS EN LA
			// CERVECERIA

			// CREO EL PEDIDO DE LA MESA1
			ArrayList<Pedido> pedidoInicialmesa1 = new ArrayList<Pedido>();
			pedidoInicialmesa1.add(pedido1);
			pedidoInicialmesa1.add(pedido2);

			// PEDIDO POSTERIOR MESA1
			ArrayList<Pedido> pedidoPosteriormesa1 = new ArrayList<Pedido>();
			pedidoPosteriormesa1.add(pedido3);
			// TOMO LAS COMANDAS

			cerveceria1.tomarComanda(mesa1, pedidoInicialmesa1);
			System.out.println(cerveceria1.getComanda(mesa1));

			// AGREGO UN PEDIDO NUEVO A LA COMANDA DE LA MESA1
			cerveceria1.tomarComanda(mesa1, pedidoPosteriormesa1);

			ArrayList<Pedido> pedidoParcialMesa1 = new ArrayList<Pedido>();

			// VERIFICO QUE SE HAYA A�ADIDO BIEN EL PEDIDO NUEVO DE MILANESA A LA COMANDA
			// EXISTENTE
			Comanda PruebaComanda = cerveceria1.getComandasAbiertas().get(0);
			pedidoParcialMesa1 = PruebaComanda.getListaProductos();
			System.out.println(pedidoParcialMesa1.get(2).getProducto().getNombre());

			// PROMOCIONES
			ArrayList<String> diasPromo = new ArrayList<String>();
			diasPromo.add("LUNES");
			diasPromo.add("VIERNES");

			// HAMBURGUESA EN 2X1 LUNES Y VIERNES -> VER BIEN COMO PONER
			// DTO_PORCANT_PRECIOUNIT SI NO SE USA!
			PromoProducto promoHamburguesa = new PromoProducto(diasPromo, hamburguesa, true, false, 0, 0, true);
			cerveceria1.addPromoProd(promoHamburguesa);

			// PROMO TEMPORAL

			PromoTemporal promoViernes = new PromoTemporal(diasPromo, "Lunes y Viernes descuento", "tarjeta", 50, true,
					false);
			cerveceria1.addPromoTemp(promoViernes);

			// DTO X CANTIDAD MILANESA

			PromoProducto promoPorCantMilanesa = new PromoProducto(diasPromo, milanesa, false, true, 2, 700, true);
			cerveceria1.addPromoProd(promoPorCantMilanesa);

			// PRUEBA DE LAS PROMOS

			cerveceria1.cerrarMesa(mesa1, "hola");
			System.out.println(cerveceria1.getVentas().get(0).getTotal());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
