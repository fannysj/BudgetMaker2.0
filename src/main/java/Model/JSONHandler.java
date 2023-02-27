package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class JSONHandler {
    private static JSONHandler instance = null;
    String filePath = "budget.json";

    public static synchronized JSONHandler getInstance(){
        if(instance == null){
            instance = new JSONHandler();
        }
        return instance;
    }

    /**
     *
     * @param budget Current budget to add to Json
     */
    public void addNewBudgetToJsonFile(Budget budget){
        JSONObject jsonObj = getExistingJSON();
        JSONObject jsonBudget = formatBudgetToJSON(budget);
        JSONArray budgetsArray = new JSONArray();
        if(!jsonObj.isEmpty()){
            budgetsArray = (JSONArray) jsonObj.get("budgets");
        }
        budgetsArray.add(jsonBudget);
        jsonObj.put("budgets", budgetsArray);
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(jsonObj.toString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param budget formating budget to json format
     * @return jsonObjct of Budget
     */

    private JSONObject formatBudgetToJSON(Budget budget){
        JSONObject budgetObject = new JSONObject();

        budgetObject.put("bId", budget.getId());
        budgetObject.put("bName", budget.getName());
        budgetObject.put("bAmount", budget.getBudgetAmount());
        BudgetModel model = budget.getBudgetModel();
        budgetObject.put("bModel", formatBudgetModelToJSON(model));

        return budgetObject;
    }

    private JSONObject formatBudgetModelToJSON(BudgetModel budgetModel){
        JSONObject budgetModelObject = new JSONObject();
        JSONArray cList = new JSONArray();
        for(Category c : budgetModel.getCategoryList()){
            JSONObject category = formatCategoryToJSON(c);
            cList.add(category);
        }

        budgetModelObject.put("cList", cList);

        return budgetModelObject;
    }

    private JSONObject formatCategoryToJSON(Category category){
        JSONObject categoryObject = new JSONObject();
        categoryObject.put("cName", category.getName());
        categoryObject.put("cAmount", category.getGoalAmount());
        JSONArray tList = new JSONArray();
        for(Transaction t : category.getTransactionsList()){
            JSONObject transaction = formatTransactionToJSON(t);
            tList.add(transaction);
        }
        categoryObject.put("transactions", tList);
        return categoryObject;
    }

    private JSONObject formatTransactionToJSON(Transaction transaction){
        JSONObject transactionObject = new JSONObject();
        transactionObject.put("tName", transaction.getName());
        transactionObject.put("tAmount", transaction.getTransactionAmount());
        transactionObject.put("tNotes", transaction.getNotes());
        //transactionObject.put("tDate", transaction.getDate());
        return transactionObject;
    }

    private JSONObject getExistingJSON() {
        try {
            File file = new File(filePath);
            if(file.length() == 0){
                return new JSONObject();
            }
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(filePath));
            return jsonObject;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public Set<Integer> getIds(){
        JSONObject jsonObj = getExistingJSON();
        Set<Integer> setOfIds = new HashSet<>();
        if(jsonObj.isEmpty()) {
            return setOfIds;
        }
        JSONArray budgets = (JSONArray) jsonObj.get("budgets");
        for(Object budget : budgets){ //JSONArray consists of plain Objects
            JSONObject b = (JSONObject) budget;
            int id = (int) (long) b.get("bId");
            setOfIds.add(id);
        }
        return setOfIds;
    }

    public void getBudgetsFromJsonFile() {
        JSONObject jsonObj = getExistingJSON();
        if(jsonObj.isEmpty()){
            return;
        }
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        try{
            User user = gson.fromJson(new FileReader("budget.json"), User.class);
            List<Budget> budgets = user.getBudgetList();
            for(Budget budget : budgets){
                System.out.println(budget.getId());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Budget> getListOfBudgetFromJsonFile() throws FileNotFoundException {
        JSONObject jsonObj = getExistingJSON();
        if (jsonObj.isEmpty()) {
            return new ArrayList<>();
        }
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        try {
            User user = gson.fromJson(new FileReader("budget.json"), User.class);
            List<Budget> budgets = user.getBudgetList();
            return budgets;
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
}
