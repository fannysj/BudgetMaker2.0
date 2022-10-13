package Model;

import com.example.budgetmaker2_0.GsonTester;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
        return "Student [ budget: " + budget + " ]";
    }

     public void GsonGoals(){
         Budget tester;
         try{
             tester = new Budget(getBudget());
             tester.writeJSON(tester);
             Budget budget1 = tester.readJSON();
             System.out.println(budget1);
         } catch(
                 IOException e) {
             e.printStackTrace();
         }
     }

    private void writeJSON(Budget budget) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileWriter writer = new FileWriter("student.json");
        writer.write(gson.toJson(budget));
        writer.close();
    }

    private Budget readJSON() throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader("student.json"));

        Budget budget = gson.fromJson(bufferedReader, Budget.class);
        System.out.println(budget);
        return budget;
    }
}




class Student {
    private String name;
    private int budget;
    public Student(){}

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getBudget() {
        return budget;
    }
    public void setBudget(int age) {
        this.budget = age;
    }
    public String toString() {
        return "Student [ name: "+name+", age: "+ budget+ " ]";
    }
}


