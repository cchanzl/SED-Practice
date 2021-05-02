package Q4;

import java.util.ArrayList;
import java.util.List;

public class Model {

 private final List<Integer> numbers = new ArrayList<>();
 private int max;
 private double mean;
 private Updatable observer;

  public void action(int n) {
    numbers.add(n);
    max = Math.max(max, n);
    mean = numbers.stream().mapToInt(val -> val).average().orElse(0.0);
    observer.update(max, mean);
 }

 public void addObserver(Updatable observer){
      this.observer = observer;
 }

}
