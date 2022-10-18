package Model;

import java.time.LocalDate;
import java.util.List;

/**
 * This class is the highest level of abstraction in our program. It is a facade for the BudgetModel.
 */

public class Budget {


    private int budget;
    private String name;

    private final int id;

    BudgetModel model;

    /**
     * Constructor of budget, we set the parameters through user input
     * @param budget the start amount of the budget
     * @param name the name of the budget
     * @param id the identification of the budget
     */

    public Budget(int budget, String name, int id) {
        this.budget = budget;
        this.name = name;
        this.id = id;
        createBudgetModel();
    }

    private void createBudgetModel(){
        this.model = new BudgetModel(this.budget);
    }


    /**
     * Getters
     * @return parameters in budgetmodel
     */

    public Budget getBudget () {
        return this;
    }

    public int getId(){
        return this.id;
    }

    public List<Category> getCategoryList(){
        return this.model.getCategoryList();
    }

    public Category getCategory(int i) {
        return this.model.getCategory(i);
    }

    public int getTotalGoalAmountOfCategories(){
        return this.model.TotalGoalAmountOfCategories();
    }

    public int getStartAmount(){
        return this.model.getStartAmount();
    }

    public BudgetModel getBudgetModel(){
        return this.model;
    }

    public int getBudgetAmount(){
        return this.budget;
    }

    public int getAmountLeft (){
        return this.model.getAmountLeft();
    }

    public int getCurrentAmount(){
        return this.model.budgetCurrentAmount();
    }

    public void setBudget (int budget){
        this.budget = budget;
    }

    /**
     * Adds a transaction to a specific category's transactions list
     */

    public void addTransactionsToCategoryTransactionList (){
        this.model.addTemporaryTransactionsToCategoryTransactionList();
    }

    public Transaction createNewTransaction(int amount, String name, String note, LocalDate date, int i){
        return this.model.createNewTransaction(amount, name, note, i, date);
    }

    @Override
    public String toString () {
        return "Budget [ Din budget: " + budget + "kr ]";
    }

}
