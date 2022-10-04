package Model;

import java.time.LocalDate;

public class Transaction {
    private double transactionAmount;
    private String name;
    private String notes;
    private Category category;
    private LocalDate date;


    public Transaction(Double amount, String name, String notes, Category category, LocalDate date){
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

