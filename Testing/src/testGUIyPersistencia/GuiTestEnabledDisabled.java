package testGUIyPersistencia;

import java.awt.AWTException;
import java.awt.Robot;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import negocio.ControladorAdmin;
import util.ControladorTestGUI;
import util.TestGIUUtils;
import vista.VProducto;

public class GuiTestEnabledDisabled {
	Robot robot;
	ControladorTestGUI controlador;

	public GuiTestEnabledDisabled() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
		}
	}

	@Before
	public void setUp() throws Exception {
		controlador = new ControladorTestGUI();
		controlador.setVista(new VProducto(), "RegistroProd");
	}

	@After
	public void tearDown() throws Exception {
		controlador.getVista().cerrarse();
	}

	@Test
	public void testVacios() {
		JButton aceptar = (JButton) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "btnAPLICAR");
		Assert.assertFalse("El boton de aceptar deberia estar deshablitado", aceptar.isEnabled());
	}

	@Test
	public void testRegSoloNombre() {

		JTextField nombre = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "textNombre");
		JButton aceptar = (JButton) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "btnAPLICAR");

		TestGIUUtils.clickComponent(nombre, robot);
		TestGIUUtils.tipeaTexto("Hamburguesa", robot);

		Assert.assertFalse("El boton de aceptar deberia estar deshablitado", aceptar.isEnabled());
	}

	@Test
	public void testRegSoloPrecioVenta() {
		robot.delay(TestGIUUtils.getDelay());

		JTextField pVenta = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "textVenta");
		JButton aceptar = (JButton) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "btnAPLICAR");

		TestGIUUtils.clickComponent(pVenta, robot);
		TestGIUUtils.tipeaTexto("200", robot);
		Assert.assertFalse("El boton de aceptar deberia estar deshablitado", aceptar.isEnabled());
	}

	@Test
	public void testRegSoloPrecioCosto() {
		robot.delay(TestGIUUtils.getDelay());

		JTextField pCosto = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "textCosto");
		JButton aceptar = (JButton) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "btnAPLICAR");

		TestGIUUtils.clickComponent(pCosto, robot);
		TestGIUUtils.tipeaTexto("150", robot);
		Assert.assertFalse("El boton de aceptar deberia estar deshablitado", aceptar.isEnabled());
	}

	@Test
	public void testRegSoloStock() {
		robot.delay(TestGIUUtils.getDelay());

		JTextField stock = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(),
				"textFieldStock");
		JButton aceptar = (JButton) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "btnAPLICAR");

		TestGIUUtils.clickComponent(stock, robot);
		TestGIUUtils.tipeaTexto("50", robot);
		Assert.assertFalse("El boton de aceptar deberia estar deshablitado", aceptar.isEnabled());
	}

	@Test
	public void testRegCuatroLlenos() {
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
		TestGIUUtils.tipeaTexto("250", robot);
		TestGIUUtils.clickComponent(stock, robot);
		TestGIUUtils.tipeaTexto("30", robot);

		Assert.assertTrue("El boton de aceptar deberia estar hablitado", aceptar.isEnabled());
	}

	@Test
	public void testRegSinStock() {
		robot.delay(TestGIUUtils.getDelay());

		JTextField nombre = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "textNombre");
		JTextField pCosto = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "textCosto");
		JTextField pVenta = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "textVenta");
		JButton aceptar = (JButton) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "btnAPLICAR");

		TestGIUUtils.clickComponent(nombre, robot);
		TestGIUUtils.tipeaTexto("Hamburguesa", robot);
		TestGIUUtils.clickComponent(pCosto, robot);
		TestGIUUtils.tipeaTexto("150", robot);
		TestGIUUtils.clickComponent(pVenta, robot);
		TestGIUUtils.tipeaTexto("250", robot);

		Assert.assertFalse("El boton de aceptar deberia estar deshablitado", aceptar.isEnabled());
	}

	@Test
	public void testRegSinPrecioVenta() {
		robot.delay(TestGIUUtils.getDelay());

		JTextField nombre = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "textNombre");
		JTextField pCosto = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "textCosto");
		JTextField stock = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(),
				"textFieldStock");
		JButton aceptar = (JButton) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "btnAPLICAR");

		TestGIUUtils.clickComponent(nombre, robot);
		TestGIUUtils.tipeaTexto("Hamburguesa", robot);
		TestGIUUtils.clickComponent(pCosto, robot);
		TestGIUUtils.tipeaTexto("150", robot);
		TestGIUUtils.clickComponent(stock, robot);
		TestGIUUtils.tipeaTexto("50", robot);

		Assert.assertFalse("El boton de aceptar deberia estar deshablitado", aceptar.isEnabled());
	}

	@Test
	public void testRegSinNombre() {
		robot.delay(TestGIUUtils.getDelay());

		JTextField pCosto = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "textCosto");
		JTextField pVenta = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "textVenta");
		JTextField stock = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(),
				"textFieldStock");
		JButton aceptar = (JButton) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "btnAPLICAR");

		TestGIUUtils.clickComponent(pCosto, robot);
		TestGIUUtils.tipeaTexto("150", robot);
		TestGIUUtils.clickComponent(pVenta, robot);
		TestGIUUtils.tipeaTexto("250", robot);
		TestGIUUtils.clickComponent(stock, robot);
		TestGIUUtils.tipeaTexto("50", robot);

		Assert.assertFalse("El boton de aceptar deberia estar deshablitado", aceptar.isEnabled());
	}

	@Test
	public void testRegSinPrecioCosto() {
		robot.delay(TestGIUUtils.getDelay());

		JTextField nombre = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "textNombre");
		JTextField pVenta = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "textVenta");
		JTextField stock = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(),
				"textFieldStock");
		JButton aceptar = (JButton) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "btnAPLICAR");

		TestGIUUtils.clickComponent(nombre, robot);
		TestGIUUtils.tipeaTexto("Hamburguesa", robot);
		TestGIUUtils.clickComponent(pVenta, robot);
		TestGIUUtils.tipeaTexto("250", robot);
		TestGIUUtils.clickComponent(stock, robot);
		TestGIUUtils.tipeaTexto("50", robot);

		Assert.assertFalse("El boton de aceptar deberia estar deshablitado", aceptar.isEnabled());
	}

	@Test
	public void testRegSinStockNiNombre() {
		robot.delay(TestGIUUtils.getDelay());

		JTextField pCosto = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "textCosto");
		JTextField pVenta = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "textVenta");
		JButton aceptar = (JButton) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "btnAPLICAR");

		TestGIUUtils.clickComponent(pCosto, robot);
		TestGIUUtils.tipeaTexto("150", robot);
		TestGIUUtils.clickComponent(pVenta, robot);
		TestGIUUtils.tipeaTexto("250", robot);

		Assert.assertFalse("El boton de aceptar deberia estar deshablitado", aceptar.isEnabled());
	}

	@Test
	public void testRegSinStockNipCosto() {
		robot.delay(TestGIUUtils.getDelay());

		JTextField nombre = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "textNombre");
		JTextField pVenta = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "textVenta");
		JButton aceptar = (JButton) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "btnAPLICAR");

		TestGIUUtils.clickComponent(nombre, robot);
		TestGIUUtils.tipeaTexto("Hamburguesa", robot);
		TestGIUUtils.clickComponent(pVenta, robot);
		TestGIUUtils.tipeaTexto("250", robot);

		Assert.assertFalse("El boton de aceptar deberia estar deshablitado", aceptar.isEnabled());
	}

	@Test
	public void testRegSinStockNipVenta() {
		robot.delay(TestGIUUtils.getDelay());

		JTextField nombre = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "textNombre");
		JTextField pCosto = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "textCosto");
		JButton aceptar = (JButton) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "btnAPLICAR");

		TestGIUUtils.clickComponent(nombre, robot);
		TestGIUUtils.tipeaTexto("Hamburguesa", robot);
		TestGIUUtils.clickComponent(pCosto, robot);
		TestGIUUtils.tipeaTexto("150", robot);

		Assert.assertFalse("El boton de aceptar deberia estar deshablitado", aceptar.isEnabled());
	}

	@Test
	public void testRegSinPrecioVentaNiNombre() {
		robot.delay(TestGIUUtils.getDelay());

		JTextField pCosto = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "textCosto");
		JTextField stock = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(),
				"textFieldStock");
		JButton aceptar = (JButton) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "btnAPLICAR");

		TestGIUUtils.clickComponent(pCosto, robot);
		TestGIUUtils.tipeaTexto("150", robot);
		TestGIUUtils.clickComponent(stock, robot);
		TestGIUUtils.tipeaTexto("50", robot);

		Assert.assertFalse("El boton de aceptar deberia estar deshablitado", aceptar.isEnabled());
	}

	@Test
	public void testRegSinPrecioVentaNipCosto() {
		robot.delay(TestGIUUtils.getDelay());

		JTextField nombre = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "textNombre");
		JTextField stock = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(),
				"textFieldStock");
		JButton aceptar = (JButton) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "btnAPLICAR");

		TestGIUUtils.clickComponent(nombre, robot);
		TestGIUUtils.tipeaTexto("Hamburguesa", robot);
		TestGIUUtils.clickComponent(stock, robot);
		TestGIUUtils.tipeaTexto("50", robot);

		Assert.assertFalse("El boton de aceptar deberia estar deshablitado", aceptar.isEnabled());
	}

	@Test
	public void testRegSinNombreNipCosto() {
		robot.delay(TestGIUUtils.getDelay());

		JTextField pVenta = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "textVenta");
		JTextField stock = (JTextField) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(),
				"textFieldStock");
		JButton aceptar = (JButton) TestGIUUtils.getComponentForName((JFrame) controlador.getVista(), "btnAPLICAR");

		TestGIUUtils.clickComponent(pVenta, robot);
		TestGIUUtils.tipeaTexto("250", robot);
		TestGIUUtils.clickComponent(stock, robot);
		TestGIUUtils.tipeaTexto("50", robot);

		Assert.assertFalse("El boton de aceptar deberia estar deshablitado", aceptar.isEnabled());
	}

}
