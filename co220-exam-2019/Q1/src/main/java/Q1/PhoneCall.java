package Q1;

import static java.time.temporal.ChronoUnit.MINUTES;

import java.time.LocalTime;

public class PhoneCall {

  private static final long PEAK_RATE = 25;
  private static final long OFF_PEAK_RATE = 10;

  private final String caller;
  private final String callee;

  private Clock clock;
  private LocalTime startTime;
  private LocalTime endTime;

  public PhoneCall(String caller, String callee) {
    this.caller = caller;
    this.callee = callee;
    this.clock = new MainClock();
  }

  public PhoneCall(String caller, String callee, Clock clock) {
    this.caller = caller;
    this.callee = callee;
    this.clock = clock;
  }

  public void start() { startTime = clock.setStart(); }

  public void end() {endTime = clock.setEnd(); }

  public void charge(CentralSystem billingSystem) { billingSystem.addBillItem(caller, callee, priceInPence()); }

  public void charge() {
    BillingSystem.getInstance().addBillItem(caller, callee, priceInPence());
  }

  private long priceInPence() {
    if (clock.isAfter(LocalTime.of(9, 00)) && clock.isBefore(LocalTime.of(18, 00))) {
      return duration() * PEAK_RATE;
    } else {
      return duration() * OFF_PEAK_RATE;
    }
  }

  private long duration() {
    return MINUTES.between(startTime, endTime) + 1;
  }

}
