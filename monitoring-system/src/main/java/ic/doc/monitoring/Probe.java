package ic.doc.monitoring;

public interface Probe {
    public boolean passes();
    public String getFailureDescription();
}
