package Model;

import com.google.gson.JsonArray;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class SortCategory {

    public List<Transaction> transactionsList;


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
