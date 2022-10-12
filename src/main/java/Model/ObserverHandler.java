package Model;

import java.util.ArrayList;
import java.util.List;

public class ObserverHandler {

    private final static List<Observer> observers = new ArrayList<>();


    public void addObserver(Observer observer){
        observers.add(observer);
    }

    public void removeObserver(Observer observer){
        observers.remove(observer);
    }

    public static void notifyAllObserver(){
        for (Observer observer : observers)
        {
            observer.update();
        }
    }
}
