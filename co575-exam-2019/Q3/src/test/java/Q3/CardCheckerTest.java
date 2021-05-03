package Q3;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class CardCheckerTest {
    // implement your tests here

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();
    Observer observer1 = context.mock(Observer.class, "obs1");
    Observer observer2 = context.mock(Observer.class, "obs2");

    final CardChecker cardchecker = new CardChecker();
    String twelve = "111122223333";
    String fourOnes = "1111";

    @Test
    public void checkCardHasTwelveDigits(){
        context.checking(
                new Expectations(){
                    {
                        never(observer1).alert(twelve);
                    }
        });
        cardchecker.addObserver(observer1);
        cardchecker.validate(twelve);
    }

    @Test
    public void checkCardHasFourDigits(){
        context.checking(
                new Expectations(){
                    {
                        exactly(1). of(observer1).alert(fourOnes);
                        exactly(1). of(observer2).alert(fourOnes);
                    }
                });
        cardchecker.addObserver(observer1);
        cardchecker.addObserver(observer2);
        cardchecker.validate(fourOnes);
    }

    @Test
    public void checkObserverCanBeRemoved(){
        context.checking(
                new Expectations(){
                    {
                        exactly(1). of(observer2).alert(fourOnes);
                    }
                });
        cardchecker.addObserver(observer1);
        cardchecker.addObserver(observer2);
        cardchecker.removeObserver(observer1);
        cardchecker.validate(fourOnes);
    }
}
