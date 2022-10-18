package com.example.budgetmaker2_0;

import Model.Budget;
import Model.BudgetModel;
import Model.Category;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;


import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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



    public void createNewBudget(int value, String name) throws IOException {

        readJSON();

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
        //BudgetModel model = new BudgetModel(budget);
        //Skicka med json-objektet till budgetmodel
        BudgetModel model = budget.getBudgetModel();

        ModelList.add(model);
        BudgetList.add(budget);
        SerializeBudgets();

    }

    public BudgetModel getCurrentBudget(){
        return this.budget.getBudgetModel();
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
        GsonBuilder gsonBuilder = new GsonBuilder();

        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());

        Gson gson = gsonBuilder.create();

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

        GsonBuilder builder = new GsonBuilder();

        builder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());

        Gson gson = builder.create();

        BufferedReader bufferedReader = new BufferedReader(
                new FileReader("budget.json"));

        Type type = new TypeToken<List<Budget>>(){}.getType();

        String json = bufferedReader.readLine();

        System.out.println(json);

        if(json != null) {
            BudgetList = gson.fromJson(json, type);
        }
    }
}

class LocalDateSerializer implements JsonSerializer< LocalDate > {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");

    @Override
    public JsonElement serialize(LocalDate localDate, Type srcType, JsonSerializationContext context) {
        return new JsonPrimitive(formatter.format(localDate));
    }
}

class LocalDateDeserializer implements JsonDeserializer < LocalDate > {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
    }
}