package Model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class TextClass {

    public TextClass() {}

   /*public void addTransaction() {
        JSONObject newTrans = new JSONObject();
        newTrans.put("name", name);
        newTrans.put("date", date);
        newTrans.put("budgetPostName", budgetPostName);
        newTrans.put("amount", amount);
        newTrans.put("description", description.toLowerCase(Locale.ROOT));
        openSetters();
        JSONArray transactions = (JSONArray) getTransactionsJSONObj().get("transactions");
        transactions.add(newTrans);
        oldDB.put("transactions", transactions);
        closeSetter();
    }*/

    public static void SerializeBudgets(Budget currentBudget) throws IOException {
        //User user = User.getInstance();
        //List<Budget> budgetList = user.getBudgetList();

        File file = new File("budget.txt");
        String budgetString = (currentBudget.toString());
        FileWriter writer = new FileWriter(file, true);
        writer.write(budgetString);
        writer.close();

    }

    public static String readFromFile() throws IOException {
        Path filePath = Path.of("/Users/fannysjostrom/IdeaProjects/BudgetMaker2.0/budget.txt");
        return Files.readString(filePath);
    }
}
