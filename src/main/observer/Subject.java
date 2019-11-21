package observer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    public List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer o) {
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }

    public void notifyObservers() throws IOException {
        for (Observer o : observers) {
            o.update();
        }
    }

}
