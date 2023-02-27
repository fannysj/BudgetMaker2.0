package Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;


public class User {

    private static User instance = new User();

    private Budget budget;
    int maxId = 0;

    Random r = new Random();

    //private PastBudget pb = new PastBudget();

    private User(){
    }

    public static User getInstance(){
        return instance;
    }

    @SerializedName("budgets")
    @Expose
    private List<Budget> BudgetList = new ArrayList<>();



    public void createNewBudget(int value, String name) throws IOException {

        JSONHandler.getInstance().getBudgetsFromJsonFile();

        budget = new Budget(value, name, generateRandomId());
    }

    private int generateRandomId(){
        Set<Integer> idSet = JSONHandler.getInstance().getIds();

        int possibleId = r.nextInt(100000);
        while(idSet.contains(possibleId)){
            possibleId = r.nextInt(100000);
        }
        return possibleId;
    }

    public List<Budget> getBudgetList() {
        return BudgetList;

    }

    public Budget getBudget(){
        return this.budget;
    }


    public void addBudgetToPastBudgetList(Budget currentBudget){
        JSONHandler.getInstance().addNewBudgetToJsonFile(currentBudget);
    }

}