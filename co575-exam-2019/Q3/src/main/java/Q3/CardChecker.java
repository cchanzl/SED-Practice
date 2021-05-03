package Q3;

import java.util.List;
import java.util.ArrayList;

public class CardChecker {
    private List<Observer> observerList = new ArrayList<>();
    private int cardLength = 12;

    public void validate(String s) {
        if (s.length() != cardLength ) {
            for(Observer obs: observerList) obs.alert(s);
        }
    }

    public void addObserver(Observer observer) {
        this.observerList.add(observer);
    }

    public void removeObserver(Observer observer) {
        this.observerList.remove(observer);
    }
}
