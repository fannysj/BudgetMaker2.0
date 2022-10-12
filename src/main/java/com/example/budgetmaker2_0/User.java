package com.example.budgetmaker2_0;

import Model.Budget;
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

    Gson gson = new Gson();

    private static User instance = new User();

    private User(){
    }

    public static User getInstance(){
        return instance;
    }


    private List<BudgetModel> ModelList = new ArrayList<>();

    public void createNewBudget(int value){
        Budget budget = new Budget(value);
        BudgetModel budgetModel = new BudgetModel();
        budgetModel.setStartAmount(budget.getBudget());
        ModelList.add(budgetModel);
        budget.GsonGoals();
    }

    public BudgetModel getBudgetModel(){
        return ModelList.get(ModelList.size()-1);
    }

    public List<Category> getCategoryList(){
        return getBudgetModel().getCategoryList();
    }


}
