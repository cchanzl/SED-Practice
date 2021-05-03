package Q1;

import java.time.LocalTime;

public interface Clock {
    public LocalTime setStart();
    public LocalTime setEnd();
    public boolean isAfter(LocalTime time);
    public boolean isBefore(LocalTime time);
}
