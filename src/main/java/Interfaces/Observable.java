package Interfaces;

/**
 * Interface for observable classes
 */

public interface Observable {
    void subscribe(Observer observer);
    void notifyObservers();

}
