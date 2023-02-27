package Model;

import com.google.gson.JsonArray;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

/**
 * This class represent the structure of the budget. It consists of categories and transactions and
 * handles calculations regarding the budgets current amount.
 */

public class BudgetModel {
    public List<Category> categoryList = new ArrayList<>();

    public List<Transaction> transactions = new ArrayList<>();

    private List<FixedCost> fixedCosts = new ArrayList<>();

    private List<FixedCost> reccuringfixedCostList = new ArrayList<>();


    private int StartAmount;
    private int amountSpent = 0;


    /**
     * Constructor of BudgetModel
     * Four set categories
     * @param startAmount the start amount of the budget
     */
    public BudgetModel(int startAmount){

        newCategory("Mat",0);
        newCategory("Shopping", 0);
        newCategory("Nöje", 0);
        newCategory("Övrigt",0);
        this.StartAmount = startAmount;
        this.fixedCosts = new ArrayList<>();
        for (FixedCost fc : FixedCost.reccuringfixedCostList) {
            if (!fixedCosts.contains(fc)) {
                fixedCosts.add(fc);
            }
        }
    }
    /**
     * Add fixed cost to list of fixed costs
     * @param fixedCost fixed cost object
     */

    public void addFixedCost(FixedCost fixedCost) {
        if (fixedCost.isRecurring()) {
            reccuringfixedCostList.add(fixedCost);
        } else {
            fixedCosts.add(fixedCost);
        }
    }
    /**
     * Delete fixed cost from list of fixed costs
     * @param name name of fixed cost
     */

    public void deleteFixedCostmodel(String name) {
        for (FixedCost fixedCost : fixedCosts) {
            if (fixedCost.getFixedcostName().equals(name)) {
                fixedCosts.remove(fixedCost);
                break;
            }
        }
        for (FixedCost fixedCost : reccuringfixedCostList) {
            if (fixedCost.getFixedcostName().equals(name)) {
                reccuringfixedCostList.remove(fixedCost);
                break;
            }
        }
    }
    public void setStartAmount(int startAmount){
        this.StartAmount = startAmount;
    }

    public void newCategory (String name, int goalamount) {
        Category category = new Category(name, goalamount);
        categoryList.add(category);
    }

    /**
     * Getters
     * @param i what category we want
     * @return the parameters of the class BudgetModel
     */
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
        return transactions;
    }
    public List<FixedCost> getFixedCostList() {
        return fixedCosts;
    }

    public int getAmountLeft(){
        return (getStartAmount() - budgetCurrentAmount());
    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> allTransactions = new ArrayList<>();
        for (Category c : categoryList) {
            allTransactions.addAll(c.getTransactionsList());
        }
        return allTransactions;
    }
    /**
     * Calculates the amount spent in the categories
     * @return the spent amount in all categories put together
     */

    public int budgetCurrentAmount(){
        amountSpent = 0;
        for (Category c : categoryList){
            amountSpent += c.getSpentAmount();
        }
        return amountSpent;
    }

    /**
     * Calculates the goal amount (budget cap) in categories
     * @return the goal amount in all categories put together
     */
    public int TotalGoalAmountOfCategories(){
        int totalGoalAmount = 0;
        for (Category c : categoryList){
            totalGoalAmount += c.getGoalAmount();
        }
        return totalGoalAmount;
    }

    public Transaction createNewTransaction (int amount, String name, String note, int i, LocalDate date) {
        Transaction t = getCategory(i).newTransaction(amount,name,note,date);
        transactions.add(t);
        return t;
    }


    /**
     * detele transaction from temporary transactionlist
     * @param t transaction that going to be deleted
     */

    public void deleteTransaction(Transaction t){
        for(Transaction tr : transactions){
            if (t == tr){
                transactions.remove(tr);
            }
            t.getCategory().notifyObservers();
        }

    }


    /**
     * Adds temporary transactions to a transaction list that their specific category holds
     */
    public void addTemporaryTransactionsToCategoryTransactionList() {
        for (Transaction t : transactions) {
            t.getCategory().addTransactionToList(t);
        }
        transactions.clear();
    }

}