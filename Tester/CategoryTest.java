import Model.Category;
import Model.Transaction;
import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

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
        List cl = c.getTransactionsList();
        assertTrue(cl.size() == 1);

    }

    @Test
    public void addingTransactionsUpdateSpentAmount(){
        c.addTransactionToList(tran);
        c.updateSpentAmount();
        assertEquals(c.spentAmount,tran.getTransactionAmount());
    }

    @Test
    public void addingTransactionDecreasesAmountLeft(){
        c.addTransactionToList(tran);
        int al = c.AmountLeftToSpend();
        assertTrue(al == 900);
    }

    @Test
    public void deletingTransactionsFromList(){
        c.addTransactionToList(tran);
        Transaction t = c.newTransaction(300, "Shopping", "Jacka", d);
        c.addTransactionToList(t);
        List tl = c.getTransactionsList();
        int before = tl.size();
        c.deleteTransactionFromList();
        int after = tl.size();
        assertNotEquals(before, after);

    }

    @Test
    public void testingGetSpentAmount(){
        c.addTransactionToList(tran);
        int sa = c.getSpentAmount();
        assertTrue(sa == 100);
    }

//Sort metoderna ska testat också

}


