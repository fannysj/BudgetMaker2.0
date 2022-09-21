package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.jar.Attributes;

public class Category {
    private String name;

    private double amount;


    public Category(String name){
        this.name = name;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }


}
