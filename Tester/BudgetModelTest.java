import Model.Budget;
import Model.BudgetModel;
import Model.Category;
import org.junit.Test;


import static org.junit.Assert.*;

public class BudgetModelTest {

    BudgetModel b = new BudgetModel(new Budget(1000, 0));
    Category shopping = new Category("Shopping", 200);
    Category transport = new Category("Transport", 100);

    public BudgetModelTest() {


    }
}

