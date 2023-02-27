package Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

/**
 * This class instantiate a new transaction instances
 */

public class Transaction {
    @SerializedName("tAmount")
    @Expose
    private int transactionAmount;
    @SerializedName("tName")
    @Expose
    private String name;
    @SerializedName("tNotes")
    @Expose
    private String notes;
    @SerializedName("tDate")
    @Expose
    private LocalDate date;

    private Category category;

    /**
     * Constructor of Transaction.
     * @param amount amount of the transaction
     * @param name  name of transaction
     * @param notes note of the transaction
     * @param date  date of the transaction
     * @param c transactions category
     */


    public Transaction(int amount, String name, String notes, LocalDate date, Category c){
        this.transactionAmount = amount;
        this.name = name;
        this.notes = notes;
        this.date = date;
        this.category = c;
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

    public LocalDate getDate(){
        return date;
    }

    public LocalDate setDate(int year, int month, int day){
        date = LocalDate.of(year,month,day);
        return date;
    }

    public Category getCategory(){
        return this.category;
    }


}