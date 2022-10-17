package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BudgetModel {
    public List<Category> categoryList = new ArrayList<>();

    public List<Transaction> transactions = new ArrayList<>();

    Budget budget;
    private int StartAmount;
    private int amountSpent = 0;
    private int amountLeft = 0;

    public BudgetModel(Budget budget){

        newCategory("Mat",2000);
        newCategory("Shopping", 2000);
        newCategory("Nöje", 1000);
        newCategory("Övrigt",100);
        this.budget = budget;
        this.StartAmount = budget.getBudget();

    }



    public void setStartAmount(int startAmount){
        this.StartAmount = startAmount;
    }

    public void newCategory (String name, int goalamount) {
        Category category = new Category(name, goalamount);
        categoryList.add(category);
    }

    // Getters
    public Category getCategory(int i){
        return categoryList.get(i);
    }

    public int getStartAmount(){
        return StartAmount;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public List<Transaction> getTransactionList(){
        return transactions;
    }

    public int getAmountLeft(){
        return (getStartAmount() - budgetCurrentAmount());
    }


    //Total mängd av spenderade pengar i varje kategori
    public int budgetCurrentAmount(){
        amountSpent = 0;
        for (Category c : categoryList){
            amountSpent += c.getSpentAmount();
        }
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

    public Transaction createNewTransaction (int amount, String name, String note, int i, LocalDate date) {
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

    public void addTemporaryTransactionsToCategoryTransactionList(){
        for(Transaction t : transactions){
            t.getCategory().addTransactionToList(t);
        }

    }


}

