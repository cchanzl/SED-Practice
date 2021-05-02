package Q3;

import java.util.List;
import java.util.ArrayList;

public class CardChecker {
    private List<Observer> observerList = new ArrayList<>();

    public void validate(String s) {
        if (s.length() != 12 ) {
            for(Observer obs: observerList){
                obs.alert(s);
            }
        }
    }

    public void addObserver(Observer observer) {
       this.observerList.add(observer);
    }

    public int checkNumberOfObserver(){return observerList.size();}

    public Observer getObserver(Observer observer){return observerList.get(observerList.size() - 1);}

    public void removeObserver(Observer observer) {
        if (observerList.size() - 1 == -1) return;
        observerList.remove(observerList.size() - 1);
    }
}
