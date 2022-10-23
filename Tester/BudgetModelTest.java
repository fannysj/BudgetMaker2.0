import Model.Budget;
import Model.BudgetModel;
import Model.Category;
import Model.Transaction;
import org.junit.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Testing for BudgetModel class. Everything is tested.
 */

public class BudgetModelTest {

    Budget budget = new Budget(1000,"hej",1);

    BudgetModel b = new BudgetModel(budget.getBudgetAmount());
    Category shopping = new Category("Shopping", 200);
    Category transport = new Category("Transport", 100);
    LocalDate d = LocalDate.now();
    Transaction t1 = new Transaction(50, "Jacka", "Zara", d, shopping);
    Transaction t2 = new Transaction(100, "Biljett", "Buss", d, transport);



    @Test
    public void testingGettersAndSettersForBudget(){
        BudgetModel copy = new BudgetModel(budget.getBudgetAmount());
        copy.setStartAmount(500);
        int bsa = b.getStartAmount();
        int csa = copy.getStartAmount();
        assertNotEquals(bsa,csa);

    }

    @Test
    public void newCategoriesShouldBeAddedToList(){
        b.newCategory("Alkohol", 2000);
        b.newCategory("Godis", 1000);
        List cl = b.getCategoryList();
        assertTrue(cl.size() == 6); //Fyra hårkodade sen innan

    }

    @Test
    public void newTransactionsShouldBeAddedToList(){
        b.createNewTransaction(50, "Sushi", "Gott", 2, d);
        b.createNewTransaction(70, "Jacka", "Fin", 3, d);
        List tl = b.getTransactionList();
        assertTrue(tl.size() == 2);

    }

    @Test
    public void addingTransactionsShouldChangeSpentAmount(){
        b.createNewTransaction(50, "Sushi", "Gott", 2, d);
        b.createNewTransaction(50, "Jacka", "Fin", 3, d);
        b.addTemporaryTransactionsToCategoryTransactionList();
        int ca = b.budgetCurrentAmount();
        assertTrue(ca == 100);
    }

    @Test
    public void addingTransactionShouldChangeAmountLeftToSpend(){
        b.createNewTransaction(50, "Jacka", "Fin", 3, d);
        b.addTemporaryTransactionsToCategoryTransactionList();
        int al = b.getAmountLeft();
        assertTrue(al == 950);
    }

    @Test
    public void gettingTheTotalOfCategoriesGoalAmount(){
        List cl = b.getCategoryList();
        cl.add(shopping);
        cl.add(transport);
        int tga = b.TotalGoalAmountOfCategories();
        assertTrue(tga == 300);

    }

    @Test
    public void shouldReturnAListWithAllTransactions(){
        b.categoryList.add(shopping);
        b.categoryList.add(transport);
        shopping.addTransactionToList(t1);
        transport.addTransactionToList(t2);
        List allTran = b.getAllTransactions();
        assertEquals(allTran.size(),2);
    }

}

