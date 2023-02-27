package Model;

import java.time.LocalDate;
import java.util.*;
import Interfaces.Observer;
import Interfaces.Observable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category implements Observable {
    @SerializedName("cName")
    @Expose
    private String name;
    @SerializedName("cAmount")
    @Expose
    private int goalAmount;

    public int spentAmount;
    @SerializedName("transactions")
    @Expose
    private List<Transaction> transactionsList = new ArrayList<>();
    private List<Observer> observerList = new ArrayList<>();

    /**
     * Constructor of Category
     * @param name name of the category
     * @param goalAmount goal amount of the category
     */
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

    public List<String> getTransactionStrings(){
        List<String> transactionList = new ArrayList<>();
        for (Transaction t : transactionsList){
            transactionList.add("\n" + this.getName() +
                    ":\nTransaction namn: " + t.getName() +
                    ",\nBelopp: " + t.getTransactionAmount() + " kr"+
                    ",\nAnteckning: " + t.getNotes() +
                    "\n -----------------------------------------------");
        }
        return transactionList;
    }

    public int getGoalAmount() {
        return goalAmount;
    }

    public int getSpentAmount() {
        updateSpentAmount();
        return spentAmount;
    }

    public List getObserverList(){
        return observerList; }


    public Transaction newTransaction(int amount, String name, String note, LocalDate date) {
        return new Transaction(amount, name, note, date, this);
    }

    /**
     * Adds a new transaction to the list of transactions
     * @param expense The transaction to be added
     */
    public void addTransactionToList(Transaction expense){
        transactionsList.add(expense);
        notifyObservers();

    }

    /**
     * Update method for current spent amount
     * For all transactions in list
     */
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
        }

        return amountLeft;
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

