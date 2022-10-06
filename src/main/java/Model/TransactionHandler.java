//package Model;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.List;
//
//public class TransactionHandler {
//    Category category;
//
//    // Transaction Methods
//
//    public void addMultipleTransactions(Transaction t){
//        List<Transaction> Temp = new ArrayList<>();
//        Temp.add(t);
//        for (Transaction o: Temp){
//            placeIntoTransactionList(o);
//        }
//    }
//
//    public void placeIntoTransactionList(Transaction t){
//        transactionsList.add(t);
//    }
//
//
//
//
//    public void updateSpentAmount(){
//        spentAmount = 0;
//        for (Transaction t : transactionsList){
//            spentAmount += t.getTransactionAmount();
//        }
//    }
//
//    public void printTransactioninList(){
//        for (Transaction t : transactionsList){
//            t.printTransaction();
//        }
//    }
//
//    public double AmountLeftToSpend(){
//        updateSpentAmount();
//        double amountLeft = goalAmount-spentAmount;
//        if (amountLeft<0){
//            System.out.println("Du överstiger ditt mål med " + -amountLeft + " kr");
//        }
//
//        return amountLeft;
//    }
//
//
//    //Sort transaction
//    public void sortByAmount() {
//        Collections.sort(transactionsList, new Comparator<Transaction>() {
//            public int compare(Transaction t1, Transaction t2) {
//                return Double.compare(t1.getTransactionAmount(), t2.getTransactionAmount());
//            }
//        });
//
//    }
//
//    public void sortByDate(){
//        Collections.sort(transactionsList, new Comparator<Transaction>() {
//            @Override
//            public int compare(Transaction o1, Transaction o2) {
//                return o1.getDate().compareTo(o2.getDate());
//            }
//        });
//    }
//
//    public void deleteTransactionFromList(){
//        transactionsList.remove(transactionsList.size()-1);
//        System.out.println(transactionsList.get(0).getName());
//    }
//
//
//    //Setters
//    public void setName(String name){
//        this.name = name;
//    }
//
//    public void setGoalAmount(double goalAmount){
//        this.goalAmount = goalAmount;
//    }
//
//    public void setSpentAmount(double spentAmount){
//        this.spentAmount = spentAmount;
//    }
//
//
//    // Getters
//    public String getName() {
//        return name;
//    }
//
//    public List<Transaction> getTransactionsList(){
//        return transactionsList;
//    }
//
//
//    public double getGoalAmount() {
//        return goalAmount;
//    }
//
//    public double getSpentAmount() {
//        updateSpentAmount();
//        return spentAmount;
//    }
//
//}


    //Change Transaction


    //kopierar:
//    public void changeOldTransaction(Transaction T){
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
