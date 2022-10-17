
import Model.Budget;
import Model.BudgetModel;
import Model.Category;
import Model.Transaction;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class TransactionTest {

    BudgetModel b = new BudgetModel(new Budget(1000, 0));
    Category c = new Category("Livsmedel", 700);
    LocalDate d = LocalDate.now();
    Transaction t = new Transaction(50, "Godis", "Snacks", c, d);

}
