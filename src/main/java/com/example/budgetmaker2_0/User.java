package com.example.budgetmaker2_0;

import Model.Budget;
import Model.BudgetModel;
import Model.Category;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


import java.io.*;
import java.lang.reflect.Type;
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

    private List<BudgetModel> ModelList = new ArrayList<>();

    private List<Budget> BudgetList = new ArrayList<>();



    public void createNewBudget(int value, int id) throws IOException {

        Budget budget = new Budget(value, id);
        BudgetModel model = new BudgetModel(budget);
        //Skicka med json-objektet till budgetmodel
        ModelList.add(model);
        BudgetList.add(budget);


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

    public void SerializeBudgets() throws IOException {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Budget>>(){}.getType();
        File file = new File("budget.json");
        String json = gson.toJson(BudgetList,type);
        FileWriter writer = new FileWriter(file);
        writer.write(json);
        writer.close();

    }

    private void writeJSON() throws IOException {

    }

    private void readJSON() throws IOException {
        Gson gson = new Gson();
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader("budget.json"));

        Type type = new TypeToken<List<Budget>>(){}.getType();

        String json = bufferedReader.readLine();

        List<Budget> budgetlist = gson.fromJson(json, type);


        //Budget budget = gson.fromJson(bufferedReader, Budget[].class);
        System.out.println("\n \n\n\n\n " + bufferedReader.readLine() + "\n\n\n\n\n");

        System.out.println("\n \n\n\n\n " + budgetlist.size()+ "");

    }




}
