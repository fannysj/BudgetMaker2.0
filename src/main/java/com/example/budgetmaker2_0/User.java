package com.example.budgetmaker2_0;

import Model.BudgetModel;
import Model.Category;
import Model.Transaction;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class User {

    public static final String CONFIG_FILE = "config.txt";

    Gson gson = new Gson();

    private static User instance = new User();

    private User(){


    }

    public static User getInstance(){
        return instance;
    }


    private List<BudgetModel> ModelList = new ArrayList<>();

    public void createNewBudget(int value){
        BudgetModel budgetModel = new BudgetModel();
        budgetModel.setStartAmount(value);
        ModelList.add(budgetModel);

    }

    public BudgetModel getBudgetModel(){
        return ModelList.get(ModelList.size()-1);
    }

    public List<Category> getCategoryList(){
        return getBudgetModel().getCategoryList();
    }

    public static void initConfig(){
        Gson gson;
        Writer writer;
        try {
            gson = new Gson();
            writer = new FileWriter(CONFIG_FILE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        gson.toJson(instance.getBudgetModel(), writer);

    }



}
