package Model;

import com.example.budgetmaker2_0.User;

import java.io.*;
import java.util.List;

public class GsonClass {

    public GsonClass() {}



    public static void SerializeBudgets() throws IOException {
        User user = User.getInstance();
        List<Budget> budgetList = user.getBudgetList();

        File file = new File("confix.txt");
        String json = (budgetList.toString());
        FileWriter writer = new FileWriter(file, true);
        writer.write(json);
        writer.close();
    }

    public static void readFromFile(List<Budget> budgetList) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(
                new FileReader("confix.txt"));

        String json = bufferedReader.readLine();

        System.out.println(json);




    }
}

