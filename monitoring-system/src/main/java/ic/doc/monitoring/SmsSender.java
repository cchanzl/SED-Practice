package ic.doc.monitoring;

public class SmsSender implements Alerter {

    // imagine this class really does SMS sending
    @Override
    public void send(String msisdn, String msg) {
        System.out.println("SMS: " + msisdn + " :" + msg);
    }
}
