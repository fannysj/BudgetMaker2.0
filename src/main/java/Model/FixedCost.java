package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FixedCost {
    private int cost;
    private String name;
    private boolean isRecurring;

    /**
     * Constructor of FixedCost
     * @param name name of the fixed cost
     * @param cost amount of the fixed cost
     * @param isRecurring - whether the fixed cost is recurring or not
     *
     */
    private static List<FixedCost> fixedCostList = new ArrayList<>();
    public static List<FixedCost> reccuringfixedCostList = new ArrayList<>();



    public FixedCost(String name, int cost, boolean isRecurring) {
        this.name = String.valueOf(name);
        this.cost = cost;
        this.isRecurring = isRecurring;
        if (isRecurring) {
            reccuringfixedCostList.add(this);
        } else {
            fixedCostList.add(this);
        }
    }
    /**
     * Getter method for isRecurring variable
     * @return - the value of the isRecurring variable
     */

    public boolean isRecurring() {
        return isRecurring;
    }

    public void setRecurring(boolean recurring) {
        isRecurring = recurring;
    }
    /**
     * Setter method for name variable
     * @param name - the new value of the name variable
     */
    public void setFixedCostName(String name) {
        this.name = name;

    }
    /**
     * Setter method for cost variable
     * @param amount - the new value of the cost variable
     */
    public void setFixedcostAmount(int amount) {
        this.cost = amount;
    }
    /**
     * Getter method for cost variable
     * @return - the value of the cost variable
     */

    public int getFixedcostAmount(){
        return cost;
    }
    /**
     * Getter method for name variable
     * @return - the value of the name variable
     */

    public String getFixedcostName(){
        return name;
    }
    /**
     * Returns the total cost of all fixed costs (recurring and non-recurring)
     * @return - the total cost of all fixed costs
     */

    public static int getTotalCost() {
        int totalCost = 0;
        for (FixedCost fixedCost : fixedCostList) {
            totalCost += fixedCost.getFixedcostAmount();
        }
        for (FixedCost fixedCost : reccuringfixedCostList) {
            totalCost += fixedCost.getFixedcostAmount();
        }
        return totalCost;
    }
    /**
     * Returns the total cost of all recurring fixed costs
     * @return - the total cost of all recurring fixed costs
     */
    public static int getTotalrecurringCost() {
        int totalCost = 0;
        for (FixedCost fixedCost : reccuringfixedCostList) {
            totalCost += fixedCost.getFixedcostAmount();
        }
        return totalCost;
    }
/**
 * Updates the name and cost of a fixed cost
 * @param name - the new name of the fixed cost
 * @param cost - the new cost of the fixed
 *
 */

    public void updateFixedCost(String name, int cost) {
        for (FixedCost fixedCost : fixedCostList) {
            if (fixedCost.getFixedcostName().equals(name)) {
                fixedCost.setFixedCostName(name);
                fixedCost.setFixedcostAmount(cost);
            }
        }
        for (FixedCost fixedCost : reccuringfixedCostList) {
            if (fixedCost.getFixedcostName().equals(name)) {
                fixedCost.setFixedCostName(name);
                fixedCost.setFixedcostAmount(cost);
            }
        }
    }

//    public void deleteFixedCost(String name) {
//        for (FixedCost fixedCost : fixedCostList) {
//            if (fixedCost.getFixedcostName().equals(name)) {
//                fixedCostList.remove(fixedCost);
//                break;
//            }
//        }
//        for (FixedCost fixedCost : reccuringfixedCostList) {
//            if (fixedCost.getFixedcostName().equals(name)) {
//                reccuringfixedCostList.remove(fixedCost);
//                break;
//            }
//        }
//    }
    public static int getNumberOfFixedCosts() {
        return fixedCostList.size() + reccuringfixedCostList.size();
    }

    @Override
    public String toString() {
        if (isRecurring){
        return "Återkommande fast utgift:" +
                name + "=" + cost + "kr";
    }
        else
        {
            return "Fast utgift:" +
                    name + "=" + cost + "kr";
        }
    }
//eftersom jag inte kunde testa klassen med junit så gjorde jag tester på detta vis.
    public static void main(String[] args) {
        FixedCost rent = new FixedCost("Rent", 5000, true);
        FixedCost electricity = new FixedCost("Electricity", 1000, false);
        FixedCost parking = new FixedCost("Parking", 500, true);
        FixedCost food = new FixedCost("Food", 1000, false);

        System.out.println("Electricity: " + electricity);
        rent.updateFixedCost("Rent", 6000);
        System.out.println("Updated Rent: " + rent);

        BudgetModel budgetModel = new BudgetModel(0);
//      budgetModel.addFixedCost(rent);
        budgetModel.addFixedCost(electricity);
        budgetModel.deleteFixedCostmodel("Parking");

        System.out.println("Total cost of all fixed costs: " + FixedCost.getTotalCost());
        System.out.println("Total recurring cost : " + FixedCost.getTotalrecurringCost());
        System.out.println("Fixed costs in the budget model: " + budgetModel.getFixedCostList());
        System.out.println("Fixed cost in the budget (adds created fixed costs): " + budgetModel.getFixedCostList());

    }

}








