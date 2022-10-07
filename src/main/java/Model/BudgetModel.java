package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;

public class BudgetModel implements Serializable {
    public List<Category> categoryList = new ArrayList<>();

    private double StartAmount;
    public double amountSpent = 0;
    private double amountLeft = 0;

    public BudgetModel(){

        newCategory("Mat",2000);
        newCategory("Shopping", 2000);
        newCategory("Nöje", 1000);
        newCategory("Övrigt",100);

        Category mat = categoryList.get(0);
        Category shop = categoryList.get(1);
        Category nöje = categoryList.get(2);
        Category övrigt = categoryList.get(3);

    }


    public void setStartAmount(int startAmount){
        this.StartAmount = startAmount;
    }

    public double getStartAmount(){
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



    //Total mängd av spenderade pengar i varje kategori
    public double currentAmount(){
        amountSpent = 0;
        for (Category c : categoryList){
            amountSpent += c.getSpentAmount();
        }
        System.out.println(amountSpent);
        return amountSpent;
    }

    //Total mängd av utgiftsmål för alla kategorier
    public double TotalGoalAmountOfCategories(){
        double totalGoalAmount = 0;
        for (Category c : categoryList){
            totalGoalAmount += c.getGoalAmount();
        }
        return totalGoalAmount;
    }

    // Hur mycket pengar finns kvar att portionera ut till kategorierna
    public void moneyLeftToDivide(){
        double tots = TotalGoalAmountOfCategories();
        double totalAmountLeft = StartAmount - tots;

        System.out.println(totalAmountLeft);
    }

    public double getAmountLeft(){
        return getStartAmount() - currentAmount();
    }

    public void addTransaction(int amount, String name, String note, int i, LocalDate date) {
        getCategory(i).newTransaction(amount,name,note,date);


    }

}

