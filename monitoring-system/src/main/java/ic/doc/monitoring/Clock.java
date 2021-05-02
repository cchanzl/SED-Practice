package ic.doc.monitoring;

import java.time.LocalTime;

public interface Clock {
    public LocalTime now();
}
