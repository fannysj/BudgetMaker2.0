package Model;



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