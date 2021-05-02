package ic.doc.monitoring;

import org.junit.Test;

import static org.junit.Assert.*;

public class MonitorTest{
  @Test
  public void sendsAnEmailWhenAProbeFails(){
    new Monitor(new FailingProbe()).check(); 
  }
  
  
}
