package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.CancellationException;

public class BudgetModel implements Serializable {
    public List<Category> categoryList = new ArrayList<>();

    public List<Transaction> transactionList = new ArrayList<>();

    private int StartAmount;
    public int amountSpent = 0;
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
        return transactionList;
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

    // Hur mycket pengar finns kvar att portionera ut till kategorierna
    public void moneyLeftToDivide(){
        int tots = TotalGoalAmountOfCategories();
        int totalAmountLeft = StartAmount - tots;

        System.out.println(totalAmountLeft);
    }

    public int getAmountLeft(){
        return (int) (getStartAmount() - currentAmount());
    }

    public void addTransaction(int amount, String name, String note, int i, LocalDate date) {
        getCategory(i).newTransaction(amount,name,note,date);
        updateTransactionList();

    }

    private void updateTransactionList() {
        transactionList.clear();
        for(Category c : categoryList){
            transactionList.addAll(c.getTransactionsList());
        }
    }

}

