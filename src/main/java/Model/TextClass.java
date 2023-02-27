package Model;

import java.io.*;
import java.util.List;

public class TextClass {

    public TextClass() {}



    public static void SerializeBudgets() throws IOException {
        User user = User.getInstance();
        List<Budget> budgetList = user.getBudgetList();

        File file = new File("budget.txt");
        String budgetListString = (budgetList.toString());
        FileWriter writer = new FileWriter(file, true);
        writer.write(budgetListString);
        writer.close();
    }

    public static void readFromFile(List<Budget> budgetList) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(
                new FileReader("budget.txt"));

        String json = bufferedReader.readLine();
    }
}
