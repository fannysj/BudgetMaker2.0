package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class User {

    public static User instance = new User();

    public Budget budget;

    public User(){
    }

    public static User getInstance(){
        return instance;
    }

    public List<Budget> BudgetList = new ArrayList<>();



    public void createNewBudget(int value, String name) throws IOException {

        //GsonClass.readJSON(BudgetList);

        int maxId = 0;
        try{
            for (Budget b : BudgetList) {
                if(b.getId() > maxId)
                    maxId = b.getId();
            }
        }catch (NullPointerException e){
            System.out.println("FROM CREATE: " +e.getMessage());
        }

        budget = new Budget(value, name, maxId+1);

        //Skicka med json-objektet till budgetmodel

        BudgetList.add(budget);

    }

    //public void saveBudget() throws IOException {
    //    GsonClass.SerializeBudgets();
    //}

    public List<Budget> getBudgetList() {
        return BudgetList;
    }

    public Budget getBudget(){
        return this.budget;
    }

    public void nextCurrentBudget(){
        int indexOfBudget = budget.getId();
        if (indexOfBudget+1 >= BudgetList.size()){
            indexOfBudget =- 1;
        }

        this.budget = BudgetList.get(indexOfBudget-1);
    }

    public void getPreviousBudget(){
        int indexofbudget = BudgetList.indexOf(this.budget);
        this.budget = BudgetList.get(indexofbudget-1);
    }

}