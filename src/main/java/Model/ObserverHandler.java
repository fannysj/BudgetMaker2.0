package Model;

import java.util.ArrayList;
import java.util.List;

public class ObserverHandler {

    private final static List<TransactionObserver> observers = new ArrayList<>();


    public void addObserver(TransactionObserver observer){
        observers.add(observer);
    }

    public void removeObserver(TransactionObserver observer){
        observers.remove(observer);
    }

    public static void notifyAllObserver(){
        for (TransactionObserver observer : observers)
        {
            observer.update();
        }
    }
}
