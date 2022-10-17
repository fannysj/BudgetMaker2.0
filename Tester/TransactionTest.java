
import Model.Budget;
import Model.BudgetModel;
import Model.Category;
import Model.Transaction;

import java.time.LocalDate;

public class TransactionTest {

    BudgetModel b = new BudgetModel(new Budget(0, 1000));
    Category c = new Category("Livsmedel", 700);
    LocalDate d = LocalDate.now();
    Transaction t = new Transaction(50, "Godis", "Snacks", c, d);

}
