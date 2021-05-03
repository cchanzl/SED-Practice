package Q1;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.time.LocalTime;

public class PhoneCallTest {

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();
  CentralSystem billingSystem = context.mock(CentralSystem.class);

  @Test
  public void testThatPeakTimeCallsAreChargedCorrectly() throws Exception {
    context.checking(new Expectations(){
      {
        exactly(1).of(billingSystem).addBillItem("+447770123456", "+4479341554433", 2*25);
      }
    });

    PhoneCall call = new PhoneCall("+447770123456", "+4479341554433", new PeakClock());
    call.start();
    call.end();
    call.charge(billingSystem);
  }

  @Test
  public void testThatOffPeakTimeCallsAreChargedCorrectly() throws Exception {
    context.checking(new Expectations(){
      {
        exactly(1).of(billingSystem).addBillItem("+447770123456", "+4479341554433", 2*10);
      }
    });

    PhoneCall call = new PhoneCall("+447770123456", "+4479341554433", new OffPeakClock());
    call.start();
    call.end();
    call.charge(billingSystem);
  }

  private class PeakClock implements Clock{
    @Override
    public LocalTime setStart(){return LocalTime.of(1, 00);}

    @Override
    public LocalTime setEnd(){return LocalTime.of(1, 1);}

    @Override
    public boolean isAfter(LocalTime time){return true;}

    @Override
    public boolean isBefore(LocalTime time){return true;}
  }

  private class OffPeakClock implements Clock{
    @Override
    public LocalTime setStart(){return LocalTime.of(1, 00);}

    @Override
    public LocalTime setEnd(){return LocalTime.of(1, 1);}

    @Override
    public boolean isAfter(LocalTime time){return false;}

    @Override
    public boolean isBefore(LocalTime time){return true;}
  }


  private void waitForSeconds(int n) throws Exception {
    Thread.sleep(n * 1000);
  }

}