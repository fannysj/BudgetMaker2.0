package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BudgetModel implements Serializable {

    Budget budget;
    private List<Category> categoryList = new ArrayList<>();

    private List<Transaction> transactions = new ArrayList<>();

    private List<Transaction> recentTransactions = new ArrayList<>();

    private int BudgetStartAmount;
    private int AmountSpent = 0;

    public BudgetModel(Budget budget){
        this.budget = budget;
        this.BudgetStartAmount = budget.getBudget();

        createNewCategory("Mat",2000);
        createNewCategory("Shopping", 2000);
        createNewCategory("Nöje", 1000);
        createNewCategory("Övrigt",100);

    }

    //Getters

    public int getBudgetStartAmount(){
        return BudgetStartAmount;
    }

    public int getAmountSpent(){
        currentAmount();
        return AmountSpent;
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

    public List<Transaction> getRecentTransactions(){
        updateTransactionList();
        return recentTransactions;
    }

    //setters
    public void setStartAmount(int startAmount){
        this.BudgetStartAmount = startAmount;
    }

    // Creational

    public void createNewCategory(String name, int goalamount) {
        Category category = new Category(name, goalamount);
        categoryList.add(category);
    }

    public Transaction createNewTransaction(int amount, String name, String note, int i, LocalDate date) {
        Transaction t = getCategory(i).newTransaction(amount,name,note,date);
        transactions.add(t);
        return t;
    }


    //Total mängd av spenderade pengar i varje kategori
    public void currentAmount(){
        AmountSpent = 0;
        for (Category c : categoryList){
            AmountSpent += c.getSpentAmount();
        }
        System.out.println(AmountSpent);
    }

    public int getAmountLeft(){
        return (getBudgetStartAmount() - getAmountSpent());
    }


    private void updateTransactionList() {
        recentTransactions.clear();
        for(Category c : categoryList){
            recentTransactions.addAll(c.getTransactionsList());
        }
    }

    public void addTemporaryTransactionsToCategoryTransactionList(){
        for(Transaction t : transactions){
            t.getCategory().addTransactionToList(t);
        }
        transactions.clear();

    }


}

