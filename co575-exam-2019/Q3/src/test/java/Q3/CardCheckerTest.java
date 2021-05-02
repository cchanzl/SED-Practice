package Q3;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class CardCheckerTest {
    // implement your tests here

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();
    Observer observer = context.mock(Observer.class);

    final CardChecker cardchecker = new CardChecker();
    String twelve = "111122223333";
    String fourOnes = "1111";

    @Test
    public void checkCardHasTwelveDigits(){
        context.checking(
                new Expectations(){
                    {
                        never(observer).alert(twelve);
                    }
        });
        cardchecker.validate(twelve);
    }

    @Test
    public void checkCardHasFourDigits(){
        context.checking(
                new Expectations(){
                    {
                        exactly(2). of(observer).alert(fourOnes);
                    }
                });

        cardchecker.addObserver(observer);
        cardchecker.addObserver(observer);
        cardchecker.validate(fourOnes);
    }
}
