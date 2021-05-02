package ic.doc.monitoring;

import org.junit.Test;

import static org.junit.Assert.*;

public class MonitorTest{
  @Test
  public void sendsAnEmailWhenAProbeFails(){
    new Monitor(new FailingProbe()).check(); 
  }
  
  private static class FailingProbe implements Probe{
    @Override
    public boolean passes(){ return false;}
    
    @OVerride public String getFailureDescription(){ return "always fails";}
  }
}
}
