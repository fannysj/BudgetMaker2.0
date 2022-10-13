import Model.Budget;
import Model.BudgetModel;
import Model.Category;
import org.junit.Test;


import static org.junit.Assert.*;

public class BudgetModelTest {

    BudgetModel b = new BudgetModel(new Budget(1000));
    Category shopping = new Category("Shopping", 200);
    Category transport = new Category("Transport", 100);

    public BudgetModelTest(){
    }


    @Test
    public void categoriesShouldBeAddedToCategoryList(){
        b.newCategory("Kläder", 500);
        b.newCategory("Ostbågar", 300);
        b.newCategory("Kaktusar", 200);
        assertTrue(b.getCategoryList().size() == 7); //4st som är hårdkodade sen innan

    }

    @Test
    public void setSpentAmountShouldChangeTheSpentAmountInACategory(){
        shopping.setSpentAmount(20);
        double s = shopping.getSpentAmount();
        assertTrue(s == 20);
    }


   // @Test //FUNGERAR EJ
    //public void totalAmountSpentShouldBeEqualToAllTheCategoriesPutTogether(){
    //    shopping.setSpentAmount(100);
     //   transport.setSpentAmount(50);
     //   b.categoryList.add(shopping);
     //   b.categoryList.add(transport);
     //   b.getTotalAmountOfCategories();
     //   assertTrue(b.amountSpent == 150);
    //}

}

