package Model;

import Interfaces.Observable;
import Interfaces.Observer;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class instantiate a new transaction instances
 */

public class Transaction {
    private int transactionAmount;
    private String name;
    private String notes;
    private Category category;
    private LocalDate date;

    /**
     * Constructor of Transaction.
     * @param amount amount of the transaction
     * @param name  name of transaction
     * @param notes note of the transaction
     * @param category  category which the transaction falls under
     * @param date  date of the transaction
     */


    public Transaction(int amount, String name, String notes, Category category, LocalDate date){
        this.transactionAmount = amount;
        this.name = name;
        this.notes = notes;
        this.category = category;
        this.date = date;
    }

    //Getters
    public int getTransactionAmount(){
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


}