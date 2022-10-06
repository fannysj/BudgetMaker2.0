package Model;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class Transaction {
    private int transactionAmount;
    private String name;
    private String notes;
    private Category category;
    private LocalDate date;


    public Transaction(int amount, String name, String notes, Category category, LocalDate date){
        this.transactionAmount = amount;
        this.name = name;
        this.notes = notes;
        this.category = category;
        this.date = date;
    }

    //Getters
    public double getTransactionAmount(){
        return transactionAmount;
    }

    public String getName(){
        return name;
    }

    public String getNotes(){
        return notes;
    }

    public Category getCategory(){
        return category;
    }

    public LocalDate getDate(){
        return date;
    }

    public LocalDate setDate(int year, int month, int day){
        date = LocalDate.of(year,month,day);
        return date;
    }

    public void printTransaction(){
        System.out.println(name + " " + transactionAmount + " kr " + notes + " " + date);
    }


}

