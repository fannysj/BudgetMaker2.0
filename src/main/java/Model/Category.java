package Model;

import java.time.LocalDate;
import java.util.*;

public class Category {
    private String name;
    private int goalAmount;
    public int spentAmount;
    public List<Transaction> transactionsList = new ArrayList<>();

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
    public void newTransaction(int amount, String name, String note, LocalDate date) {
        Transaction transaction = new Transaction(amount, name, note, this, date);
        addTransactionToList(transaction);
    }

    public void addMultipleTransactions(Transaction t) {
        List<Transaction> Temp = new ArrayList<>();
        Temp.add(t);
        for (Transaction o : Temp) {
            placeIntoTransactionList(o);
        }
    }

    public void placeIntoTransactionList(Transaction t){
        transactionsList.add(t);
    }

    public void addTransactionToList(Transaction expense){
        transactionsList.add(expense);
        ObserverHandler.notifyAllObserver();
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

    public int AmountLeftToSpend(){
        updateSpentAmount();
        int amountLeft = goalAmount-spentAmount;
        if (amountLeft<0){
            System.out.println("Du överstiger ditt mål med " + -amountLeft + " kr");
        }

        return amountLeft;
    }


    //Sort transaction
    public void sortByAmount() {
        Collections.sort(transactionsList, new Comparator<Transaction>() {
            public int compare(Transaction t1, Transaction t2) {
                return Integer.compare(t1.getTransactionAmount(), t2.getTransactionAmount());
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



}

