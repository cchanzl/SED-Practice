package Q4;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class UnitTest {

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();
    Model calc = new Model();
    Updatable ui = context.mock(Updatable.class);

    @Test
    public void testMean(){
        context.checking(
                new Expectations(){
                    {
                        exactly(1).of(ui).update(3,3);
                        exactly(1).of(ui).update(11,7);
                    }
                });
        calc.addObserver(ui);
        calc.action(3);
        calc.action(11);
    }
}
