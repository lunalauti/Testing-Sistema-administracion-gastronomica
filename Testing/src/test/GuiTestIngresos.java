package test;

import java.awt.AWTException;
import java.awt.Robot;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modelo.Cerveceria;
import modelo.Producto;
import util.ControladorTestGUI;
import util.FalsoOptionPane;
import util.TestGIUUtils;
import vista.VProducto;

public class GuiTestIngresos {
	Robot robot;
	ControladorTestGUI controlador;
	FalsoOptionPane op = new FalsoOptionPane();
	EscenarioVacio escenario = new EscenarioVacio();

	public GuiTestIngresos() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
		}
	}

	@Before
	public void setUp() throws Exception {
		escenario.setUp();
		Cerveceria.getInstance().agregarProducto(new Producto("Cerveza",260,500,60));
		controlador = new ControladorTestGUI();
		controlador.setOptionPane(op);
		controlador.setVista(new VProducto(), "RegistroProd");
	}

	@After
	public void tearDown() throws Exception {
		controlador.getVista().cerrarse();
	}

	@Test
	public void testCargaOk() {
		robot.delay(TestGIUUtils.getDelay());

		JTextField nombre = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "textNombre");
		JTextField pCosto = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "textCosto");
		JTextField pVenta = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "textVenta");
		JTextField stock = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(),
				"textFieldStock");
		JButton aceptar = (JButton) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "btnAPLICAR");

		TestGIUUtils.clickComponent(nombre, robot);
		TestGIUUtils.tipeaTexto("Hamburguesa", robot);
		TestGIUUtils.clickComponent(pCosto, robot);
		TestGIUUtils.tipeaTexto("260", robot);
		TestGIUUtils.clickComponent(pVenta, robot);
		TestGIUUtils.tipeaTexto("500", robot);
		TestGIUUtils.clickComponent(stock, robot);
		TestGIUUtils.tipeaTexto("65", robot);

		// envio los datos
		TestGIUUtils.clickComponent(aceptar, robot);

		// verifico los resultados
		Assert.assertEquals("Deberia coincidir el nombre ingresado con el nombre del ultimo producto", "Hamburguesa",
				Cerveceria.getInstance().getCarta().get(Cerveceria.getInstance().getCarta().size()-1).getNombre());
	}

	@Test
	public void testProductoRepetido() {
		robot.delay(TestGIUUtils.getDelay());

		JTextField nombre = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "textNombre");
		JTextField pCosto = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "textCosto");
		JTextField pVenta = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "textVenta");
		JTextField stock = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(),
				"textFieldStock");
		JButton aceptar = (JButton) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "btnAPLICAR");

		TestGIUUtils.clickComponent(nombre, robot);
		TestGIUUtils.tipeaTexto("Cerveza", robot);
		TestGIUUtils.clickComponent(pCosto, robot);
		TestGIUUtils.tipeaTexto("100", robot);
		TestGIUUtils.clickComponent(pVenta, robot);
		TestGIUUtils.tipeaTexto("125", robot);
		TestGIUUtils.clickComponent(stock, robot);
		TestGIUUtils.tipeaTexto("300", robot);

		// envio los datos
		TestGIUUtils.clickComponent(aceptar, robot);

		// verifico los resultados
		Assert.assertEquals(
				"Mensaje incorrecto, deberia decir \"El producto " + "Cerveza"
						+ " ya existe y no puede ser agregado a la carta\"",
				"El producto Cerveza ya existe y no puede ser agregado a la carta", op.getMensaje());
	}

	@Test
	public void testSizeNombre() {
		robot.delay(TestGIUUtils.getDelay());

		JTextField nombre = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "textNombre");
		JTextField pCosto = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "textCosto");
		JTextField pVenta = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "textVenta");
		JTextField stock = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(),
				"textFieldStock");
		JButton aceptar = (JButton) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "btnAPLICAR");

		TestGIUUtils.clickComponent(nombre, robot);
		TestGIUUtils.tipeaTexto("Ham", robot);
		TestGIUUtils.clickComponent(pCosto, robot);
		TestGIUUtils.tipeaTexto("260", robot);
		TestGIUUtils.clickComponent(pVenta, robot);
		TestGIUUtils.tipeaTexto("500", robot);
		TestGIUUtils.clickComponent(stock, robot);
		TestGIUUtils.tipeaTexto("65", robot);

		// envio los datos
		TestGIUUtils.clickComponent(aceptar, robot);

		// verifico los resultados
		Assert.assertEquals(
				"Mensaje incorrecto, deberia decir \"El nombre del producto debe tener al menos 4 caracteres.\"",
				"El nombre del producto debe tener al menos 4 caracteres.", op.getMensaje());
	}

	@Test
	public void testPCostoNegativo() {
		robot.delay(TestGIUUtils.getDelay());

		JTextField nombre = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "textNombre");
		JTextField pCosto = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "textCosto");
		JTextField pVenta = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "textVenta");
		JTextField stock = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(),
				"textFieldStock");
		JButton aceptar = (JButton) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "btnAPLICAR");

		TestGIUUtils.clickComponent(nombre, robot);
		TestGIUUtils.tipeaTexto("Hamburguesa", robot);
		TestGIUUtils.clickComponent(pCosto, robot);
		TestGIUUtils.tipeaTexto("-3", robot);
		TestGIUUtils.clickComponent(pVenta, robot);
		TestGIUUtils.tipeaTexto("500", robot);
		TestGIUUtils.clickComponent(stock, robot);
		TestGIUUtils.tipeaTexto("65", robot);

		// envio los datos
		TestGIUUtils.clickComponent(aceptar, robot);

		// verifico los resultados
		Assert.assertEquals(
				"Mensaje incorrecto, deberia decir \"No se pueden dejar campos vacios ni ingresar valores negativos.\"",
				"No se pueden dejar campos vacios ni ingresar valores negativos.", op.getMensaje());
	}

	@Test
	public void testPVentaNegativo() {
		robot.delay(TestGIUUtils.getDelay());

		JTextField nombre = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "textNombre");
		JTextField pCosto = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "textCosto");
		JTextField pVenta = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "textVenta");
		JTextField stock = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(),
				"textFieldStock");
		JButton aceptar = (JButton) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "btnAPLICAR");

		TestGIUUtils.clickComponent(nombre, robot);
		TestGIUUtils.tipeaTexto("Hamburguesa", robot);
		TestGIUUtils.clickComponent(pCosto, robot);
		TestGIUUtils.tipeaTexto("150", robot);
		TestGIUUtils.clickComponent(pVenta, robot);
		TestGIUUtils.tipeaTexto("-3", robot);
		TestGIUUtils.clickComponent(stock, robot);
		TestGIUUtils.tipeaTexto("65", robot);

		// envio los datos
		TestGIUUtils.clickComponent(aceptar, robot);

		// verifico los resultados
		Assert.assertEquals(
				"Mensaje incorrecto, deberia decir \"No se pueden dejar campos vacios ni ingresar valores negativos.\"",
				"No se pueden dejar campos vacios ni ingresar valores negativos.", op.getMensaje());
	}

	@Test
	public void testStockNegativo() {
		robot.delay(TestGIUUtils.getDelay());

		JTextField nombre = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "textNombre");
		JTextField pCosto = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "textCosto");
		JTextField pVenta = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "textVenta");
		JTextField stock = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(),
				"textFieldStock");
		JButton aceptar = (JButton) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "btnAPLICAR");

		TestGIUUtils.clickComponent(nombre, robot);
		TestGIUUtils.tipeaTexto("Hamburguesa", robot);
		TestGIUUtils.clickComponent(pCosto, robot);
		TestGIUUtils.tipeaTexto("150", robot);
		TestGIUUtils.clickComponent(pVenta, robot);
		TestGIUUtils.tipeaTexto("300", robot);
		TestGIUUtils.clickComponent(stock, robot);
		TestGIUUtils.tipeaTexto("-65", robot);

		// envio los datos
		TestGIUUtils.clickComponent(aceptar, robot);

		// verifico los resultados
		Assert.assertEquals(
				"Mensaje incorrecto, deberia decir \"No se pueden dejar campos vacios ni ingresar valores negativos.\"",
				"No se pueden dejar campos vacios ni ingresar valores negativos.", op.getMensaje());
	}

	@Test
	public void testPVentaMenorAPCosto() {
		robot.delay(TestGIUUtils.getDelay());

		JTextField nombre = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "textNombre");
		JTextField pCosto = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "textCosto");
		JTextField pVenta = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "textVenta");
		JTextField stock = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(),
				"textFieldStock");
		JButton aceptar = (JButton) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "btnAPLICAR");

		TestGIUUtils.clickComponent(nombre, robot);
		TestGIUUtils.tipeaTexto("Hamburguesa", robot);
		TestGIUUtils.clickComponent(pCosto, robot);
		TestGIUUtils.tipeaTexto("300", robot);
		TestGIUUtils.clickComponent(pVenta, robot);
		TestGIUUtils.tipeaTexto("150", robot);
		TestGIUUtils.clickComponent(stock, robot);
		TestGIUUtils.tipeaTexto("65", robot);

		// envio los datos
		TestGIUUtils.clickComponent(aceptar, robot);

		// verifico los resultados
		Assert.assertEquals(
				"Mensaje incorrecto, deberia decir \"El precio de venta no puede ser menor al precio de costo.\"",
				"El precio de venta no puede ser menor al precio de costo.", op.getMensaje());
	}

}
