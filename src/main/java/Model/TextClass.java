package Model;

import Model.User;
import com.google.gson.*;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

//    public static void saveFixedCostsToFile() {
//        try {
//            FileOutputStream fos = new FileOutputStream("fixed_costs.txt");
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
//            oos.writeObject(FixedCost.reccuringfixedCostList);
//            oos.close();
//            fos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void readFixedCostsFromFile() {
//        try {
//            FileInputStream fis = new FileInputStream("fixed_costs.txt");
//            ObjectInputStream ois = new ObjectInputStream(fis);
//            FixedCost.reccuringfixedCostList = (ArrayList<FixedCost>) ois.readObject();
//            ois.close();
//            fis.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

}
