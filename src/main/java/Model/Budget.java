package Model;

import java.time.LocalDate;
import java.util.List;


public class Budget {


    private int budget;
    private String name;

    private final int id;

    BudgetModel model;


    public Budget(int budget, String name, int id) {
        this.budget = budget;
        this.name = name;
        this.id = id;
        createBudgetModel();
    }

    private void createBudgetModel(){
        this.model = new BudgetModel(this.budget);
    }

    public Budget getBudget () {
        return this;
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

    public void addTransactionsToCategoryTransactionList (){
        this.model.addTemporaryTransactionsToCategoryTransactionList();
    }

    public Transaction createNewTransaction(int amount, String name, String note, LocalDate date, int i){
        return this.model.createNewTransaction(amount, name, note, i, date);
    }

    public int getId(){
        return this.id;
    }

    public List<Category> getCategoryList(){
        return this.model.getCategoryList();
    }

    public void setBudget (int budget){
        this.budget = budget;
    }
    @Override
    public String toString () {
        return "\n Budget { \n" +
                "Din budget: " + budget + "kr \n" +
                "Dina kategorier : " + getCategoryList()+ "\n" +
                "Dina transaktioner: " + model.getTransactionList()+ "\n" +
                "] \n" ;
    }

    public List<Transaction> getTransactionList() {
        return this.model.getTransactionList();
    }
}
