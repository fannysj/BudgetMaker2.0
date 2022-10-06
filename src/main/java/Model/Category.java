package Model;

import java.time.LocalDate;
import java.util.*;

public class Category {
    private String name;
    private double goalAmount;
    public double currentAmount;
    public List<Transaction> transactionsList = new ArrayList<>();
    private List<TransactionObserver> observers = new ArrayList<>();

    public Category(String name, double goalAmount) {
        this.name = String.valueOf(name);
        this.goalAmount = goalAmount;
        this.currentAmount = 0;
    }

    //Setters
    public void setName(String name){
        this.name = name;
    }

    public void setGoalAmount(double goalAmount){
        this.goalAmount = goalAmount;
    }

    public void setSpentAmount(double spentAmount){
        this.currentAmount = spentAmount;
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
        return currentAmount;
    }


    // Transaction Methods
    public void newTransaction(int amount, String name, String note, LocalDate date) {
        Transaction transaction = new Transaction(amount, name, note,this, date);
        addTransactionToList(transaction);
    }

    public void addMultipleTransactions(Transaction t){
        List<Transaction> Temp = new ArrayList<>();
        Temp.add(t);
        for (Transaction o: Temp){
            placeIntoTransactionList(o);
        }
    }

    public void placeIntoTransactionList(Transaction t){
        transactionsList.add(t);
    }

    public void addTransactionToList(Transaction expense){
        transactionsList.add(expense);
    }


    public void updateSpentAmount(){
        currentAmount = 0;
        for (Transaction t : transactionsList){
            currentAmount += t.getTransactionAmount();
        }
    }

    public void printTransactioninList(){
        for (Transaction t : transactionsList){
            t.printTransaction();
        }
    }

    public double AmountLeftToSpend(){
        updateSpentAmount();
        double amountLeft = goalAmount-currentAmount;
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


    //Change Transaction

    //kopierar:
//    public void changeTransaction(Transaction T){
//        copyTransaction(T);
//        transactionsList.set(transactionsList.indexOf(T), T);
//    }
//    public Transaction copyTransaction(Transaction t){
//        return new Transaction(t.getTransactionAmount() , t.getName(), t.getNotes(), t.getCategory(), t.getDate());
//    }
//
//    //ta bort och ersätt gammal:
//    public List<Transaction> replaceTransaction(Transaction t){
//        transactionsList.remove(t);
//        int index = transactionsList.indexOf(t);
//        Transaction newTrans = new Transaction();
//        transactionsList.set(index, newTrans);
//        return transactionsList;
//    }
//


//    // Observer
//    /**
//     * Add/Remove observer
//     *
//     * @param observer
//     */
//    public void addObserver(TransactionObserver observer){
//        this.observers.add(observer);
//    }
//
//    public void removeObserver(TransactionObserver observer){
//        this.observers.remove(observer);
//    }
//
//    public void notifyAllObserver(){
//        ArrayList<Transaction> transactionsCopy = new ArrayList<>(); //Ska vara Iterator?
//        // Collection.copy(transactionsCopy, transactionsList);
//        // Defensiv kodning
//
//        for(TransactionObserver observer: observers){
//            observer.update();
//        }
//
//    }

}

