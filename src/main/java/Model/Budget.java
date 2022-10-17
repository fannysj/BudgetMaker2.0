package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.example.budgetmaker2_0.GsonTester;
import com.google.gson.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Budget {

    @Override
    public String toString() {
        return "Budget{" +
                "budget=" + budget +
                ", id=" + id +
                '}';
    }

    private int budget;
    private int id;

    public Budget(int budget, int id) {
        this.budget = budget;
        this.id = id;

    }
    public int getBudget () {
        return budget;
    }

    public void setBudget (int budget){
        this.budget = budget;
    }
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void GsonGoals() {
        Budget tester = new Budget(budget, id);
        File input = new File("student.json");
        try {
//            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
//            JsonObject fileobject = fileElement.getAsJsonObject();
//
//            Integer thisbudget = fileobject.get("budget").getAsInt();
//            Integer thisid = fileobject.get("id").getAsInt();
//            System.out.println("newbudget:" + thisbudget + thisid);

//            JsonArray jsonArrayofObjects = fileobject.get("budget").getAsJsonArray();
//            List<Budget> budgets = new ArrayList<>();
//            for (JsonElement budgetElement : jsonArrayofObjects) {
//                JsonObject budgetJsonObject = budgetElement.getAsJsonObject();
//
//                int budget2 = budgetJsonObject.get("budget").getAsInt();
//                int id = budgetJsonObject.get("id").getAsInt();
//
//                Budget budget = new Budget(budget2, id);
//                budgets.add(budget);
//            }
            Budget budget = new Budget(getBudget(), getId());
            budget.setBudget(getBudget());
            tester.writeJSON(budget);
            Budget budget1 = tester.readJSON();
            System.out.println(budget1);

        }



        catch(
                 FileNotFoundException e) {
             e.printStackTrace();
         }
         catch(
                 IOException e) {
             e.printStackTrace();

    }
    }

    private void writeJSON(Budget budget) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileWriter writer = new FileWriter("budget.json");
        writer.write(gson.toJson(budget));
        writer.close();
    }

    private Budget readJSON() throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader("budget.json"));

        Budget budget = gson.fromJson(bufferedReader, Budget.class);
        System.out.println(budget);
        return budget;

    }
}
