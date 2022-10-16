package Model;

import java.time.LocalDate;
import java.util.*;
import Interfaces.Observer;
import Interfaces.Observable;

public class Category implements Observable {
    private String name;
    private int goalAmount;
    public int spentAmount;
    private List<Transaction> transactionsList = new ArrayList<>();
    public List<Observer> observerList = new ArrayList<>();

    public Category(String name, int goalAmount) {
        this.name = String.valueOf(name);
        this.goalAmount = goalAmount;
        this.spentAmount = 0;

    }

    //Setters
    public void setName(String name){
        this.name = name;
    }

    public void setGoalAmount(int goalAmount){
        this.goalAmount = goalAmount;
    }


    public void setSpentAmount(int spentAmount) {
        this.spentAmount = spentAmount;
    }


    // Getters
    public String getName() {
        return name;
    }

    public List<Transaction> getTransactionsList() {
        return transactionsList;
    }

    public int getGoalAmount() {
        return goalAmount;
    }

    public int getSpentAmount() {
        updateSpentAmount();
        return spentAmount;
    }

    // Transaction Methods
    public Transaction newTransaction(int amount, String name, String note, LocalDate date) {
        return new Transaction(amount, name, note, this, date);
    }

    public void addTransactionToList(Transaction expense){
        transactionsList.add(expense);
        notifyObservers();

    }

    public void updateSpentAmount(){
        spentAmount = 0;
        for (Transaction t : transactionsList){
            spentAmount += t.getTransactionAmount();
        }

    }

    public int AmountLeftToSpend(){
        updateSpentAmount();
        int amountLeft = (int) (getGoalAmount()-getSpentAmount());
        if (amountLeft<0){
            System.out.println("Du överstiger ditt mål med " + -amountLeft + " kr");
        }

        return amountLeft;
    }


    //Sort transaction
    public void sortByAmount() {
        Collections.sort(transactionsList, new Comparator<Transaction>() {
            public int compare(Transaction t1, Transaction t2) {
                return Double.compare(t1.getTransactionAmount(), t2.getTransactionAmount());
            }
        });

    }

    public void sortByDate(){
        Collections.sort(transactionsList, new Comparator<Transaction>() {
            @Override
            public int compare(Transaction o1, Transaction o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
    }

    public void deleteTransactionFromList(){
        transactionsList.remove(transactionsList.size()-1);
    }


    public void subscribe(Observer observer){
        observerList.add(observer);
    }

    public void notifyObservers(){
        for (var observer : observerList){
            observer.update(this);
        }
    }

}

