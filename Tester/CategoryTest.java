import Model.Category;
import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;

import static org.junit.Assert.assertTrue;

public class CategoryTest {

    Category c = new Category("Kategori", 1000);
    LocalDate d = LocalDate.now();

    @Test
    public void transactionsShouldBeAddedToTransactionList(){
        c.newTransaction(100, "HM", "Vinterjacka", d);
        c.newTransaction(50, "Zara", "Skor", d);
        assertTrue(c.getTransactionsList().size() == 2);

    }

    @Test
    public void addingTransactionsShouldChangeSpentAmountInCategory(){
        c.newTransaction(100, "HM", "Vinterjacka", d);
        c.newTransaction(20, "Zara", "Skor", d);
        c.updateSpentAmount();
        assertTrue(c.getSpentAmount() == 120);
    }

    @Test
   public void addingTransactionsShouldChangeAmountLeftToSpendInCategory(){
        c.newTransaction(100, "HM", "Vinterjacka", d);
        double a = c.AmountLeftToSpend();
        assertTrue(a == 900 );

    }

    @Test
    public void YouShouldBeAbleToDeleteATransaction(){
        c.newTransaction(100, "HM", "Vinterjacka", d);
        c.newTransaction(20, "Zara", "Skor", d);
        c.newTransaction(100, "Stadium", "Träningskläder", d);
        c.deleteTransactionFromList();
        assertTrue(c.getTransactionsList().size() == 2);
    }

    @Test //Fungerar ej, vet inte om jag missuppfattar hur setters fungerar?
    public void settersShouldChangeCategoryInfo(){
        Category copy = c;
        copy.setName("Alkohol");
        copy.setGoalAmount(2000);
        assertNotEquals(copy, c);
    }
}