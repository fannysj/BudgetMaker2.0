//package Model;
//
//import javafx.scene.control.TextField;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//import java.util.regex.PatternSyntaxException;
//
//
//public class SortCategory {
//
//    public List<Transaction> transactionsList = new ArrayList<>();
//
//
//    public void search(Category category, TextField searchbar) {
//        List<Transaction> matches = new ArrayList<>();
//        try {
//            Pattern pattern = Pattern.compile(String.format(".*%s.*", searchbar.getText()), Pattern.CASE_INSENSITIVE);
//            category.getTransactionsList().forEach(transaction -> {
//                Matcher m = pattern.matcher(transaction.getName());
//                if (m.matches()) {
//                    matches.add(transaction);
//                }
//            }) ;
//        } catch (PatternSyntaxException e) {
//            e.getMessage();
//        }
//        // updateTransactionList(matches);
//    }
//
//    public void sortByAmount() {
//        Collections.sort(transactionsList, new Comparator<Transaction>() {
//            public int compare(Transaction t1, Transaction t2) {
//                return Integer.compare(t1.getTransactionAmount(), t2.getTransactionAmount());
//            }
//        });
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
//}
