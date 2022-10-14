package Model;

import com.example.budgetmaker2_0.GsonTester;
import com.google.gson.*;

import java.io.*;

public class Budget {

    private int budget;

    public Budget(int budget) {
        this.budget = budget;

    }
    public int getBudget () {
        return budget;
    }
    public void setBudget ( int budget){
        this.budget = budget;
    }

    public String toString () {
        return "Budget [ Din budget: " + budget + "kr ]";
    }

     public void GsonGoals(){
         Budget tester = new Budget(budget);
         File input = new File("budget.json");
         try{
             JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
             JsonObject fileobject = fileElement.getAsJsonObject();

             String thisbudget = fileobject.get("budget").getAsString();
             System.out.println("newbudget:" + thisbudget);

             Budget budget = new Budget(getBudget());
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
