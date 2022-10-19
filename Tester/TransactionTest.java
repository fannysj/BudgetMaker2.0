import Model.Category;
import Model.Transaction;
import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDate;

/**
 * Testing for transaction class which only contains setters and getters.
 */

public class TransactionTest {

    Category c = new Category("Livsmedel", 700);
    LocalDate d = LocalDate.now();
    Transaction t = new Transaction(50, "Godis", "Snacks", c, d);

    @Test
    public void testingGetters(){
        assertSame(50, t.getTransactionAmount());
        assertSame("Godis", t.getName());
        assertSame("Snacks", t.getNotes());
        assertSame(c, t.getCategory());
        assertSame(d, t.getDate());
    }

    @Test
    public void testingSetDate(){
        t.setDate(1998,8,8);
        assertNotEquals(d,t.getDate());
    }


}
