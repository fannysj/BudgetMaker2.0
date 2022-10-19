package Model;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BudgetModel {
    public List<Category> categoryList = new ArrayList<>();

    private List<Transaction> transactions = new ArrayList<>();

    private Map<String, ArrayList<Transaction>> tempTransactions = new HashMap<>();

    private int StartAmount;
    private int amountSpent = 0;
    private int amountLeft = 0;

    public BudgetModel(int startAmount){

        newCategory("Mat",0);
        newCategory("Shopping", 0);
        newCategory("Nöje", 0);
        newCategory("Övrigt",0);

        this.StartAmount = startAmount;

    }


    public void setStartAmount(int startAmount){
        this.StartAmount = startAmount;
    }

    public void newCategory (String name, int goalamount) {
        Category category = new Category(name, goalamount);
        categoryList.add(category);
    }

    // Getters
    public Category getCategory(int i){
        return categoryList.get(i);
    }

    public int getStartAmount(){
        return StartAmount;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public List<Transaction> getTransactionList(){
        List<Transaction> transactions = new ArrayList<>();
        for(String name : tempTransactions.keySet()){
            transactions.addAll(tempTransactions.get(name));
        }
        return transactions;

    }

    public int getAmountLeft(){
        return (getStartAmount() - budgetCurrentAmount());
    }


    //Total mängd av spenderade pengar i varje kategori
    public int budgetCurrentAmount(){
        amountSpent = 0;
        for (Category c : categoryList){
            amountSpent += c.getSpentAmount();
        }
        return amountSpent;
    }

    //Total mängd av utgiftsmål för alla kategorier
    public int TotalGoalAmountOfCategories(){
        int totalGoalAmount = 0;
        for (Category c : categoryList){
            totalGoalAmount += c.getGoalAmount();
        }
        return totalGoalAmount;
    }

    public Transaction createNewTransaction (int amount, String name, String note, int i, LocalDate date) {
        Transaction t = getCategory(i).newTransaction(amount,name,note,date);
        String catName = getCategory(i).getName();
        tempTransactions.putIfAbsent(catName, new ArrayList<>());
        System.out.println("\n\n\n\n" + tempTransactions.get(catName) +"\n\n\n\n" );
        tempTransactions.get(catName).add(t);
        return t;
    }

    public void addTemporaryTransactionsToCategoryTransactionList() {
        for(Category c : categoryList){
            String name = c.getName();
            tempTransactions.putIfAbsent(name, new ArrayList<>());
            ArrayList<Transaction> categoryTransactions = tempTransactions.get(name);
            for(Transaction t : categoryTransactions){
                c.addTransactionToList(t);
            }
        }
        tempTransactions.clear();
        try{
            GsonClass.SerializeBudgets();
        }catch (IOException e){
            e.getMessage();
        }

    }


}

