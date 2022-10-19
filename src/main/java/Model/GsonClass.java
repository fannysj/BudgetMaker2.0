package Model;

import com.google.gson.*;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class GsonClass {

    public GsonClass() {}



    public static void SerializeBudgets() throws IOException {
        User user = User.getInstance();
        List<Budget> budgetList = user.getBudgetList();

        File file = new File("budget.txt");
        String json = (budgetList.toString());
        FileWriter writer = new FileWriter(file, true);
        writer.write(json);
        writer.close();
    }

    public static void readJSON(List<Budget> budgetList) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(
                new FileReader("budget.txt"));

        String json = bufferedReader.readLine();

        System.out.println(json);




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
