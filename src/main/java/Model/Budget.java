package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


import java.io.*;
import java.lang.reflect.Type;
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

    public int getBudgetCurrentAmount (){
        return this.model.budgetCurrentAmount();
    }

    public void addTransactionsToCategoryTransactionList (){
        this.model.addTemporaryTransactionsToCategoryTransactionList();
    }

    public void createNewTransaction(int amount, String name, String note, LocalDate date, int i){
        this.model.createNewTransaction(amount, name, note, i, date);
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
        return "Budget [ Din budget: " + budget + "kr ]";
    }


    public void GsonGoals() throws IOException {

        writeJSON(this);

    }




    private void writeJSON(Budget budget) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type type = new TypeToken<List<Budget>>(){}.getType();
        File file = new File("budget.json");
        String json = gson.toJson(this,type);

        System.out.println(json);
    }

    private Budget readJSON() throws FileNotFoundException {
        Gson gson = new Gson();
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader("budget.json"));

        Budget budget = gson.fromJson(bufferedReader, Budget.class);
        System.out.println(budget);
        return budget;
    }
}
