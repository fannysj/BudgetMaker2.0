package Model;

import com.example.budgetmaker2_0.transactionObserver;

import java.time.LocalDate;
import java.util.*;

public class Category {
    private String name;
    private double goalAmount;
    public double spentAmount;
    public List<Transaction> transactionsList = new ArrayList<>();
    private List<transactionObserver> observers = new ArrayList<>();

    public Category(String name, double goalAmount) {
        this.name = String.valueOf(name);
        this.goalAmount = goalAmount;
        this.spentAmount = 0;
    }

    //Setters
    public void setName(String name){
        this.name = name;
    }

    public void setGoalAmount(double goalAmount){
        this.goalAmount = goalAmount;
    }

    public void setSpentAmount(double spentAmount){
        this.spentAmount = spentAmount;
    }


    // Getters
    public String getName() {
        return name;
    }

    public List<Transaction> getTransactionsList(){
        return transactionsList;
    }


    public double getGoalAmount() {
        return goalAmount;
    }

    public double getSpentAmount() {
        updateSpentAmount();
        return spentAmount;
    }


    // Transaction Methods
    public void newTransaction(double amount, String name, String note, LocalDate date) {
        Transaction transaction = new Transaction(amount, name, note,this, date);
        addTransactionToList(transaction);
    }

    public void addmultipleTransactions(Transaction t){
        List<Transaction> Temp = new ArrayList<>();
        Temp.add(t);
        System.out.println(Temp);
        for (Transaction o: Temp){

        }

    }

    public void addTransactionToList(Transaction expense){
        transactionsList.add(expense);
    }


    public void updateSpentAmount(){
        spentAmount = 0;
        for (Transaction t : transactionsList){
            spentAmount += t.getTransactionAmount();
        }
    }

    public void printTransactioninList(){
        for (Transaction t : transactionsList){
            t.printTransaction();
        }
    }

    public double AmountLeftToSpend(){
        updateSpentAmount();
        double amountLeft = goalAmount-spentAmount;
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
        System.out.println(transactionsList.get(0).getName());
    }


    // Observer
    /**
     * Add/Remove observer
     *
     * @param observer
     */
    public void addObserver(transactionObserver observer){
        this.observers.add(observer);
    }

    public void removeObserver(transactionObserver observer){
        this.observers.remove(observer);
    }

    public void notifyAllObserver(){
        ArrayList<Transaction> transactionsCopy = new ArrayList<>(); //Ska vara Iterator?
        // Collection.copy(transactionsCopy, transactionsList);
        // Defensiv kodning

        for(transactionObserver observer: observers){
            observer.update(transactionsCopy);
        }

    }

}

