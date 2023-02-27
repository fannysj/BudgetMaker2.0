import Model.Budget;
import Model.BudgetModel;
import Model.Category;
import org.junit.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.Assert.*;

/** Testing the budget class. Since it is mostly a facade for BudgetModel we have not tested all the getters.
        * We also didn't test the toString method since we see that it works by outprint.
*/

public class BudgetTest {

    Budget b = new Budget(10000,"Januari",10);
    BudgetModel bm = b.getBudgetModel();
    Category shopping = new Category("Shopping", 200);
    Category transport = new Category("Transport", 100);
    LocalDate d = LocalDate.now();

    @Test
    public void testingGettersForBudget(){
        assertSame(10,b.getId());
        assertSame(b, b.getBudget());
    }

    @Test
    public void testingSetBudget(){
        b.setBudget(500);
        assertNotEquals(1000,b.getBudgetAmount());

    }

    @Test
    public void createAndAddTransactionToList(){
        b.createNewTransaction(10,"Jacka","HM",LocalDate.now(),3);
        b.addTransactionsToCategoryTransactionList();
        assertSame(1,b.getCategory(3).getTransactionsList().size());
    }


}
