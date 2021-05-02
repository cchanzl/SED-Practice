package ic.doc.monitoring;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Monitor extends SystemClock implements Clock {

    private List<Probe> probes;
    private final Alerter emailer; // dependency inversion by introducing an interface
    private final Alerter smsSender;
    private final Clock clock;

    public Monitor() {
        this.probes = new ArrayList<Probe>();
        this.probes.add(new WebPageProbe("http://www.google.com", "Google"));
        this.probes.add(new WebPageProbe("http://www.imperial.ac.uk/", "Imperial College"));
        emailer = Emailer.getInstance();
        smsSender = new SmsSender();
        clock = new SystemClock();
    }

    public Monitor(Alerter emailer, Alerter smsSender, Clock clock, Probe... probes){
        this.probes = new ArrayList<Probe>();
        this.probes.addAll(Arrays.asList(probes));
        this.emailer = emailer;
        this.smsSender = smsSender;
        this.clock = clock;
    }

    public void check() {

        for (Probe probe : probes) {
            if (!probe.passes()) {
                emailer.send("support@example.com", probe.getFailureDescription());

                LocalTime now = clock.now();
                if (now.getHour() >= BusinessHours.START_OF_BUSINESS &&
                        now.getHour() < BusinessHours.CLOSE_OF_BUSINESS) {
                    smsSender.send("+447777123456", probe.getFailureDescription());
                }
            }
        }
    }


    public static void main(String[] args) {
        new Monitor().check();
        System.out.println("Completed.");
    }
}