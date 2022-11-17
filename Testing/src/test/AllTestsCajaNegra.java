package test;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ TestAgregarProducto.class, TestAsignarMesaMozo.class, TestEliminacion.class })
public class AllTestsCajaNegra {

}
