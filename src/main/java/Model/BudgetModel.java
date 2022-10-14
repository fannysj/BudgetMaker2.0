package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.CancellationException;

public class BudgetModel {
    private List<Category> categoryList = new ArrayList<>();

    private List<Transaction> transactions = new ArrayList<>();

    private int StartAmount;
    private int amountSpent = 0;
    private int amountLeft = 0;

    public BudgetModel(){

        newCategory("Mat",2000);
        newCategory("Shopping", 2000);
        newCategory("Nöje", 1000);
        newCategory("Övrigt",100);

    }


    public void setStartAmount(int startAmount){
        this.StartAmount = startAmount;
    }

    public int getStartAmount(){
        return StartAmount;
    }

    public void newCategory(String name, int goalamount) {
        Category category = new Category(name, goalamount);
        categoryList.add(category);
    }

    public Category getCategory(int i){
        return categoryList.get(i);
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public List<Transaction> getTransactionList(){
        return transactions;
    }

    //Total mängd av spenderade pengar i varje kategori
    public int currentAmount(){
        amountSpent = 0;
        for (Category c : categoryList){
            amountSpent += c.getSpentAmount();
        }
        System.out.println(amountSpent);
        return amountSpent;
    }

    //Total mängd av utgiftsmål för alla kategorier
    public int TotalGoalAmountOfCategories(){
        int totalGoalAmount = 0;
        for (Category c : categoryList){
            totalGoalAmount += c.getGoalAmount();
        }
        return totalGoalAmount;
    }

    public int getAmountLeft(){
        return (getStartAmount() - currentAmount());
    }

    public Transaction addTransaction(int amount, String name, String note, int i, LocalDate date) {
        Transaction t = getCategory(i).newTransaction(amount,name,note,date);
        transactions.add(t);
        return t;
    }

    private void updateTransactionList() {
        transactions.clear();
        for(Category c : categoryList){
            transactions.addAll(c.getTransactionsList());
        }
    }

    public void addTemporaryTransactionsToTransactionList(){
        for(Transaction t : transactions){
            t.getCategory().addTransactionToList(t);
        }

    }


}

