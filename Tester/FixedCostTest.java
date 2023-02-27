import Model.BudgetModel;
import Model.FixedCost;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FixedCostTest {

    @Test
    public void testAddFixedCost() {
        FixedCost rent = new FixedCost("Rent", 5000, true);
        FixedCost electricity = new FixedCost("Electricity", 1000, false);

        BudgetModel budgetModel = new BudgetModel(0);
        budgetModel.addFixedCost(rent);
        budgetModel.addFixedCost(electricity);

        List<FixedCost> fixedCosts = budgetModel.getFixedCostList();

        Assertions.assertEquals(2, fixedCosts.size());
        Assertions.assertEquals(rent, fixedCosts.get(0));
        Assertions.assertEquals(electricity, fixedCosts.get(1));
    }

    @Test
    public void testDeleteFixedCost() {
        FixedCost rent = new FixedCost("Rent", 5000, true);
        FixedCost electricity = new FixedCost("Electricity", 1000, false);

        BudgetModel budgetModel = new BudgetModel(0);
        budgetModel.addFixedCost(rent);
        budgetModel.addFixedCost(electricity);
        budgetModel.deleteFixedCostmodel("Rent");

        List<FixedCost> fixedCosts = budgetModel.getFixedCostList();

        Assertions.assertEquals(1, fixedCosts.size());
        Assertions.assertEquals(electricity, fixedCosts.get(0));
    }

    @Test
    public void testUpdateFixedCost() {
        FixedCost rent = new FixedCost("Rent", 5000, true);

        rent.updateFixedCost("Rent", 6000);

        Assertions.assertEquals("Rent", rent.getFixedcostName());
        Assertions.assertEquals(6000, rent.getFixedcostAmount());
    }

    @Test
    public void testGetTotalCost() {
        FixedCost rent = new FixedCost("Rent", 5000, true);
        FixedCost electricity = new FixedCost("Electricity", 1000, false);

        int totalCost = FixedCost.getTotalCost();

        Assertions.assertEquals(6000, totalCost);
    }

    @Test
    public void testGetTotalrecurringCost() {
        FixedCost rent = new FixedCost("Rent", 5000, true);
        FixedCost electricity = new FixedCost("Electricity", 1000, false);

        int totalRecurringCost = FixedCost.getTotalrecurringCost();

        Assertions.assertEquals(5000, totalRecurringCost);
    }

}
