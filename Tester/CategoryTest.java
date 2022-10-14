import Model.Category;
import Model.Transaction;
import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;

import static org.junit.Assert.assertTrue;

public class CategoryTest {

    Category c = new Category("Kategori", 1000);
    LocalDate d = LocalDate.now();
    Transaction tran = c.newTransaction(100, "Willys", "Korv", d);


    @Test //set.SpentAmount körs över av get.SpentAmount
    public void testingSetterAndGetter() {
        Category original = new Category("Ostbågar", 1000);
        Category copy = new Category("Ostbågar", 1000);

        copy.setGoalAmount(500);
        int cga = copy.getGoalAmount();
        int oga = original.getGoalAmount();

        copy.setName("Kopia");
        String cn = copy.getName();
        String on = original.getName();

        copy.setSpentAmount(20);

        assertNotEquals(cga, oga);
        assertNotEquals(cn, on);
        assertTrue(copy.spentAmount == 20);
    }


    @Test
    public void CreateTransactionAndAddItToList() {
        Transaction t = c.newTransaction(300, "Shopping", "Jacka", d);
        c.addTransactionToList(t);
        assertTrue(c.transactionsList.size() == 1);

    }

    @Test
    public void updateSpentAmountBasedOnAddedTransactions(){
        c.addTransactionToList(tran);
        c.updateSpentAmount();
        assertEquals(c.spentAmount,tran.getTransactionAmount());
    }

}


