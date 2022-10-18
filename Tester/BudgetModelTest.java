import Model.Budget;
import Model.BudgetModel;
import Model.Category;
import org.junit.Test;


import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class BudgetModelTest {

    Budget budget = new Budget(1000,"hej",1);

    BudgetModel b = new BudgetModel(budget.getBudgetAmount());
    Category shopping = new Category("Shopping", 200);
    Category transport = new Category("Transport", 100);
    LocalDate d = LocalDate.now();


    @Test
    public void testingGettersAndSettersForBudget(){
        BudgetModel copy = new BudgetModel(budget.getBudgetAmount());
        copy.setStartAmount(500);
        int bsa = b.getStartAmount();
        int csa = copy.getStartAmount();
        assertNotEquals(bsa,csa);

    }

    @Test
    public void testingGettersForLists(){
        b.newCategory("Mat", 400);
        b.createNewTransaction(50, "Sushi", "Gott", 2, d);
        List cl = b.getCategoryList();
        List tl = b.getTransactionList();
        assertTrue(cl.size() == 5);
        assertEquals(tl,b.transactions);
    }

    @Test
    public void createCategoryAndAddToList(){
        b.newCategory("Alkohol", 2000);
        b.newCategory("Godis", 1000);
        assertTrue(b.categoryList.size() == 6); //Fyra hårkodade sen innan

    }

    @Test
    public void createTransactionAndAddToList(){
        b.createNewTransaction(50, "Sushi", "Gott", 2, d);
        b.createNewTransaction(70, "Jacka", "Fin", 3, d);
        assertTrue(b.transactions.size() == 2);

    }

    @Test
    public void calculatingSpentAmountForBudget(){
        b.createNewTransaction(50, "Sushi", "Gott", 2, d);
        b.createNewTransaction(50, "Jacka", "Fin", 3, d);
        b.addTemporaryTransactionsToCategoryTransactionList();
        int ca = b.budgetCurrentAmount();
        assertTrue(ca == 100);
    }

    @Test
    public void calculatingAmountLeftToSpend(){
        b.createNewTransaction(50, "Jacka", "Fin", 3, d);
        b.addTemporaryTransactionsToCategoryTransactionList();
        int al = b.getAmountLeft();
        assertTrue(al == 950);
    }

    @Test
    public void gettingTheTotalOfCategoriesGoalAmount(){
        b.categoryList.add(shopping);
        b.categoryList.add(transport);
        int tga = b.TotalGoalAmountOfCategories();
        assertTrue(tga == 5400); //goalAmount även från de hårdkodade kategorierna

    }

    //update transactionlist inte testad

}

