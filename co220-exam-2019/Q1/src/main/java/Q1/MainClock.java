package Q1;

import java.time.LocalTime;

public class MainClock implements Clock {

    @Override
    public LocalTime setStart(){return LocalTime.now();}

    @Override
    public LocalTime setEnd(){return LocalTime.now();}

    @Override
    public boolean isAfter(LocalTime time){
        return time.isAfter(LocalTime.of(9, 00));
    };

    @Override
    public boolean isBefore(LocalTime time){
        return time.isBefore(LocalTime.of(18, 00));
    };

}
