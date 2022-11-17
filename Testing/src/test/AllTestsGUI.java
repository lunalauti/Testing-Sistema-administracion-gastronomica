package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ GuiTestEnabledDisabled.class, GuiTestIngresos.class })
public class AllTestsGUI {

}
