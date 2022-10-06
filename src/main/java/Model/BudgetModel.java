package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BudgetModel implements Serializable {
    public List<Category> categoryList = new ArrayList<>();
    private final double amount;
    public double amountSpent = 0;
    private double amountLeft = 0;

    public BudgetModel(int amount){
        this.amount = amount;
        newCategory("mat",2000);
        newCategory("shopping", 2000);
        newCategory("nöje", 1000);
        newCategory("övrigt",100);

        Category mat = categoryList.get(0);
        Category shop = categoryList.get(1);
        Category nöje = categoryList.get(2);
        Category övrigt = categoryList.get(3);


        mat.newTransaction(100, "ica", "veckohandling", LocalDate.now());
        mat.newTransaction(1000, "willys", "bbq", LocalDate.now());
        shop.newTransaction(500, "hm", "latexdräkt", LocalDate.now());
        nöje.newTransaction(1000, "ticketmaster", "markoolio", LocalDate.now());

        mat.printTransactioninList();
        currentAmount();
        mat.AmountLeftToSpend();
        mat.getTransactionsList().get(0).setDate(1999,6,4);
        mat.sortByAmount();
        mat.sortByDate();
        mat.printTransactioninList();
        moneyLeftToDivide();
    }


    public void newCategory(String name, int amount) {
        Category category = new Category(name, amount);
        categoryList.add(category);
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    //Total mängd av spenderade pengar i varje kategori
    public void currentAmount(){
        amountSpent = 0;
        for (Category c : categoryList){
            amountSpent += c.getSpentAmount();
        }
        System.out.println(amountSpent);
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
        double totalAmountLeft = amount - tots;

        System.out.println(totalAmountLeft);


    }

    public void setCategoryAmount() {
    }

}

