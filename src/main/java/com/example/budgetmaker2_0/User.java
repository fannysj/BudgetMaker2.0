package com.example.budgetmaker2_0;

import Model.Budget;
import Model.BudgetModel;
import Model.Category;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class User {

    private static User instance = new User();

    private Budget budget;

    private User(){
    }

    public static User getInstance(){
        return instance;
    }


    private List<Budget> BudgetList = new ArrayList<>();

    private List<BudgetModel> ModelList = new ArrayList<>();

    public void createNewBudget(int value, int id)  {
        Budget budget = new Budget(value, id) ;
        //Skicka med json-objektet till budgetmodel
        BudgetModel budgetModel = new BudgetModel(budget);
        BudgetList.add(budget);
        ModelList.add(budgetModel);
        saveToGson();
        budget.GsonGoals();
    }

    public Budget getCurrentBudget(){
        return this.budget;
    }

    public void nextCurrentBudget(){
        int indexOfBudget = BudgetList.indexOf(this.budget);
        if (indexOfBudget+1 >= BudgetList.size()){
            indexOfBudget =- 1;
        }

        this.budget = BudgetList.get(indexOfBudget-1);
    }

    public void getPreviousBudget(){
        int indexofbudget = BudgetList.indexOf(this.budget);
        this.budget = BudgetList.get(indexofbudget-1);
    }

    public BudgetModel getBudgetModel(){
        return ModelList.get(ModelList.size()-1);
    }

    public List<Category> getCategoryList(){
        return getBudgetModel().getCategoryList();
    }

    public void saveToGson(){

        try
        {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            Writer writer = Files.newBufferedWriter(Paths.get("student.json"));

            gson.toJson(BudgetList,writer);

            writer.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public Budget readFromGson() throws FileNotFoundException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        BufferedReader reader = new BufferedReader(new FileReader("student.json"));
        Budget budget = gson.fromJson(reader, Budget.class);
        return budget;
    }


}
