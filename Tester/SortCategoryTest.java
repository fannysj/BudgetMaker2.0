//import Model.*;
//import org.junit.Test;
//import java.time.LocalDate;
//import java.util.List;
//import static org.junit.Assert.*;
//
//public class SortCategoryTest {
//
//    SortCategory sortCategory = new SortCategory();
//    BudgetModel bm = new BudgetModel(500);
//    Category c = new Category("Ost", 200);
//    Transaction t1 = c.newTransaction(10,"Cheddar", "Gott", LocalDate.of(2019,10,13));
//    Transaction t2 = c.newTransaction(10,"Herrgård", "Godare", LocalDate.of(2022,8,1));
//    Transaction t3 = c.newTransaction(10,"Parmesan", "Godast", LocalDate.of(2021,9,10));
//
//
//
//    @Test
//    public void sortingByDateShouldReturnTheNewestTransaction(){
//        c.addTransactionToList(t1);
//        c.addTransactionToList(t2);
//        c.addTransactionToList(t3);
//        System.out.println(t2.getDate());
//        System.out.println(t1.getDate());
//        sortCategory.sortByDate();
//
//        assertEquals(c.getTransactionsList().get(0).getName(),t2.getName());
//
//    }
//
//
//}
