package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Budget {



    private int budget;
    private int id;
    Map<Integer, Integer> budgets = new HashMap<>();


    public Budget(int budget, int id) {
        this.budget = budget;
        this.id = id;
        budgets.put(budget,id);



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

    public int getId(){
        return id;
    }


    private void writeJSON(Map<Integer,Integer> s) throws IOException {
        FileWriter writer = new FileWriter("student.json", true);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        writer.close();


    }

    public Budget readJSON() throws FileNotFoundException {

        BufferedReader bufferedReader = new BufferedReader(
                new FileReader("student.json"));

        System.out.println(budget);
        return this;
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


