package ic.doc.monitoring;

import java.time.LocalTime;

public class SystemClock implements Clock {
    @Override
    public LocalTime now() {
        return LocalTime.now();
    }
}
