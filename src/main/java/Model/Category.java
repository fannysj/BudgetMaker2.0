package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.jar.Attributes;

public class Category {
    ArrayList<Category> categories = new ArrayList<Category>();

    private String name;

    private int amount;


    public Category(String name, int amount){
        this.name = name;
        this.amount = amount;

        categories.add(this);

        System.out.println(categories);


    }


}
