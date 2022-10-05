package com.example.budgetmaker2_0;

import Model.BudgetModel;

import java.util.ArrayList;
import java.util.List;

public class User {

    private List<BudgetModel> ModelList = new ArrayList<>();

    public void createNewBudget(int value){
        ModelList.add(new BudgetModel(value));
    }

    public BudgetModel getBudgetModel(){
        return ModelList.get(0);
    }
}
