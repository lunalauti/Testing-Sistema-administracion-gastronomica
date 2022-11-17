package test;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ AllTestsCajaNegra.class, AllTestsGUI.class, TestCajaBlanca.class, TestPersistencia.class })
public class AllTests {

}
