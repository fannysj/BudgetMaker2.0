package com.example.budgetmaker2_0;

import Model.newBudget;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


import java.io.IOException;


public class GsonTester extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("hello-view"));
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
        GsonTester tester = new GsonTester();
              try {
                 Student student = new Student();
                 student.setBudget(10);
                 student.setName("Mahesh");
                 tester.writeJSON(student);
                 Student student1 = tester.readJSON();
                 System.out.println(student1);
              }
              catch(FileNotFoundException e) {
                 e.printStackTrace();
              }
              catch(IOException e) {
                 e.printStackTrace();
              }
           }

           private void writeJSON(Student student) throws IOException {
              GsonBuilder builder = new GsonBuilder();
              Gson gson = builder.create();
              FileWriter writer = new FileWriter("student.json");
              writer.write(gson.toJson(student));
              writer.close();
           }

           private Student readJSON() throws FileNotFoundException {
              GsonBuilder builder = new GsonBuilder();
              Gson gson = builder.create();
              BufferedReader bufferedReader = new BufferedReader(
                 new FileReader("student.json"));

              Student student = gson.fromJson(bufferedReader, Student.class);
              return student;
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