
import Model.Budget;
import Model.BudgetModel;
import Model.Category;
import Model.Transaction;

import java.time.LocalDate;

public class TransactionTest {
    Budget budget = new Budget(1000,"name",0);
    BudgetModel b = new BudgetModel(budget.getBudgetAmount());
    Category c = new Category("Livsmedel", 700);
    LocalDate d = LocalDate.now();
    Transaction t = new Transaction(50, "Godis", "Snacks", c, d);

}
