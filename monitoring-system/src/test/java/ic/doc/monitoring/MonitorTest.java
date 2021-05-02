package ic.doc.monitoring;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.time.LocalTime;

public class MonitorTest{

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();
  Alerter emailer = context.mock(Alerter.class, "emailer");
  Alerter smsSender = context.mock(Alerter.class, "smsSender");
  private final ControllableClock clock  = new ControllableClock();;

  @Test
  public void sendsAnEmailWhenAProbeFailsOutOfBusinessHours(){
    clock.currentTimeIs(6,00);
    context.checking(new Expectations(){
      {
        exactly(1).of(emailer).send("support@example.com", "always fails");
      }
    });
    new Monitor(emailer, new SmsSender(), new SystemClock(), new FailingProbe()).check();
  }

  @Test
  public void sendsAnEmailAndAnSmsWhenAProbeFailsDuringBusinessHours(){
    clock.currentTimeIs(15,00);

    context.checking(new Expectations(){
      {
        exactly(1).of(emailer).send("support@example.com", "always fails");
        exactly(1).of(smsSender).send("+447777123456", "always fails");
      }
    });

    new Monitor(emailer, smsSender, clock, new FailingProbe()).check();
  }

  // seams
  // added so that we don't include the detail of network connecting code
  private static class FailingProbe implements Probe{
    @Override
    public boolean passes(){ return false;}
    
    @Override
    public String getFailureDescription(){ return "always fails";}
  }

  private class ControllableClock implements Clock{
    private LocalTime now;

    @Override
    public LocalTime now(){return now;}

    public void currentTimeIs(int hour, int min){
        now = LocalTime.of(hour, min);
    }
  }
}

