package com.example.budgetmaker2_0;

import Model.BudgetModel;

import java.util.ArrayList;
import java.util.List;

public class User {


    private List<BudgetModel> ModelList = new ArrayList<>();

    public void createNewBudget(int value){
        BudgetModel budgetModel = new BudgetModel();
        budgetModel.setAmount(value);
        ModelList.add(budgetModel);
    }

    public BudgetModel getBudgetModel(){
        return ModelList.get(0);
    }
}
