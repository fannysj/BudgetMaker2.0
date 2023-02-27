import Model.Budget;
import Model.BudgetModel;
import Model.Category;
import Model.Transaction;
import org.junit.Test;
import Model.User;
import static org.junit.Assert.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Testing for User class. We have not tested everything since we have not been able to implement
 * everything.
 */

public class UserTest {

    User u = User.getInstance();


    @Test
    public void createBudgetAndAddToList() throws IOException {
        u.createNewBudget(100,"2022");
        u.createNewBudget(200,"2023");
        assertEquals(u.getBudgetList().size(), 2);
    }

}


