package ic.doc.monitoring;

public interface Alerter {
    public void send(String to, String msg);
}
