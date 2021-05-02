package Q3;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class ObserverTest {
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();
    Observer observer = context.mock(Observer.class);
    final CardChecker cardchecker = new CardChecker();

    @Test
    public void testAddObserver(){
        cardchecker.addObserver(observer);
        assertThat(cardchecker.getObserver(observer), equalTo(observer));
    }

    @Test
    public void testRemoveObserver(){
        cardchecker.addObserver(observer);
        cardchecker.removeObserver(observer);
        assertThat(cardchecker.checkNumberOfObserver(), equalTo(0));
    }

}
