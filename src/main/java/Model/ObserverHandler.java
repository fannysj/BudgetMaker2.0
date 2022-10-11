package Model;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.util.ArrayList;
import java.util.List;

public class ObserverHandler {

    private String transactionAmount;

    private final static List<TransactionObserver> observers = new ArrayList<>();


    public void addObserver(TransactionObserver observer){
        observers.add(observer);
    }

    public void removeObserver(TransactionObserver observer){
        this.observers.remove(observer);
    }

    public static void notifyAllObserver(int transactionAmount){
        for (TransactionObserver observer : observers)
        {
            observer.update(transactionAmount);
        }
    }
}
