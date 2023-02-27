package Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is the highest level of abstraction in our program. It is a facade for the BudgetModel.
 */

public class Budget{

    @SerializedName("bAmount")
    @Expose
    private int budget;
    @SerializedName("bName")
    @Expose
    private String name;
    @SerializedName("bId")
    @Expose
    private final int id;
    @SerializedName("bModel")
    @Expose
    BudgetModel model;

    /**
     * Constructor of budget, we set the parameters through user input
     *
     * @param budget the start amount of the budget
     * @param name   the name of the budget
     * @param id     the identification of the budget
     *               creates a BudgetModel
     */

    public Budget(int budget, String name, int id) {
        this.budget = budget;
        this.name = name;
        this.id = id;
        createBudgetModel();
    }

    private void createBudgetModel() {
        this.model = new BudgetModel(this.budget);
    }


    /**
     * Getters
     *
     * @return parameters in budgetmodel
     */

    public Budget getBudget() {
        return this;
    }

    public int getId() {
        return this.id;
    }

    public List<Category> getCategoryList() {
        return this.model.getCategoryList();
    }

    public List<String> getCategoryNameList() {
        return this.model.getCategoryNames();
    }

    public Category getCategory(int i) {
        return this.model.getCategory(i);
    }

    public int getTotalGoalAmountOfCategories() {
        return this.model.TotalGoalAmountOfCategories();
    }

    public int getStartAmount() {
        return this.model.getStartAmount();
    }

    public BudgetModel getBudgetModel() {
        return this.model;
    }

    public int getBudgetAmount() {
        return this.budget;
    }

    public int getAmountLeft() {
        return this.model.getAmountLeft();
    }

    public int getCurrentAmount() {
        return this.model.budgetCurrentAmount();
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    /**
     * Adds a transaction to a specific category's transactions list
     */

    public void addTransactionsToCategoryTransactionList (){
        this.model.addTemporaryTransactionsToCategoryTransactionList();
    }

    public List<Transaction> getAllTransaction(){
        return this.model.getAllTransactions();
    }


    public Transaction createNewTransaction(int amount, String name, String note, LocalDate date, int i){
        return this.model.createNewTransaction(amount, name, note, i, date);
    }

    public String SavedOrLost(){
        int diff = getBudgetAmount()-getCurrentAmount();
        if (diff<0){
            return "Du gick över budgeten med " +  Math.abs(diff) + "kr ";
        }
        else {
            return "Du sparade " + diff + "kr! ";
        }
    }

    @Override
    public String toString () {
        return "\nBudget  \n" +
                "Din budget:     " + budget + "kr \n" +
                "" + SavedOrLost() + " \n" +
                "Dina kategorier :  " + getCategoryNameList()+ "\n" +
                "Dina transaktioner:    " + model.getAllTransactionStrings()+ "\n" +
                " \n" ;
    }

    public void deleteTransaction(Transaction t){
        this.model.deleteTransaction(t);
    }

    public String getName() {
        return name;
    }
}
